package sachonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sachonline.data.entities.Sach;
import sachonline.data.entities.TacGia;
import sachonline.data.repositories.SachRepository;
import sachonline.data.repositories.TacGiaRepository;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sach")
public class SachController {

    @Autowired
    ServletContext servletContext;
    @Autowired
    TacGiaRepository tacGiaRepository;
    @Autowired
    SachRepository sachRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "sach/Index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String doCreate(
            @ModelAttribute("sach") Sach sach
            ,@RequestParam("lstTacgia") String lstTacgia
    ) throws IOException {
//        String rootPath = System.getProperty("catalina.home");

        // Xử lý List tác giả

        List<TacGia> lstDaDuocTao = xuLyTacGia(lstTacgia);

        sach.setLstTacGia(lstDaDuocTao);
        sachRepository.insert(sach);

//        if (!file.isEmpty()) {
//            file.getBytes();
//            String rootPath = System.getProperty("catalina.home");
//            Path path = Files.createFile(Paths.get(""));
//
//        }
        return "sach/Index";
    }

    private List<TacGia> xuLyTacGia(String lstTacgia) {
        List<String> lstTenTacGia = Arrays.asList(lstTacgia.split(","));
        List<TacGia> tatCaTacGia = tacGiaRepository.getAll();

        List<String> strTatCaTacGia = tatCaTacGia
                .stream()
                .map(TacGia::getTenTacGia)
                .collect(Collectors.toList());

        List<String> lstTacGiaCanDuocTao = null;

        // Tao danh sach chua TacGia da duoc tao roi
        List<TacGia> lstDaDuocTao = null;

        // Lấy danh sách cần đươc tạo
        try {
            lstTacGiaCanDuocTao = lstTenTacGia.stream()
                    .filter(
                            e -> !strTatCaTacGia.contains(
                                    e
                            ))
                    .collect(Collectors.toList());


            try {
//                tatCaTacGia.stream()
//                        .filter(
//                                e -> lstTenTacGia.contains(
//                                        e.getTenTacGia()
//                                ))
//                        .collect(Collectors.toList()).forEach(e -> System.out.println(e.toString()));
                lstDaDuocTao = tatCaTacGia.stream()
                        .filter(
                                e -> lstTenTacGia.contains(
                                        e.getTenTacGia()
                                ))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
                lstDaDuocTao = new ArrayList<>();
            }

            // Tạo danh sách cần được tạo
            for (String tacGia : lstTacGiaCanDuocTao) {
                TacGia tacGiaX = new TacGia();
                tacGiaX.setTenTacGia(tacGia);
                tacGiaRepository.insert(tacGiaX);

                // Lấy lại tác giả, có thêm id
                TacGia getBackTacGia = tacGiaRepository.getName(
                        tacGiaX.getTenTacGia()
                ).get(0);

                // Add nó vô list được tạo, sau khi kết thúc sẽ cho 1 list đầy đủ
                lstDaDuocTao.add(
                        getBackTacGia
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstDaDuocTao;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(Model model, @ModelAttribute("sach") Sach sach) {
        model.addAttribute("lstTacGia", tacGiaRepository.getAll());
        return "sach/Create";
    }

    @RequestMapping(value = "/update/{maSach}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long maSach) {
        Sach sach = sachRepository.findId(maSach);
        model.addAttribute("sach", sach);

        return "sach/Update";
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute("sach") Sach sach, @RequestParam("lstTacgia") String lstTacgia) {
        System.out.println(sach.toString());
        List<TacGia> lstTGia =  xuLyTacGia(lstTacgia);

        sach.setLstTacGia(lstTGia);
        sachRepository.update(sach);

        return "sach/Index";
    }


    @RequestMapping(value = "/getTacGia/{maSach}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getTacGia(@PathVariable Long maSach) {
        List<String> lstTenTacGia = sachRepository
                .findId(maSach)
                .getLstTacGia()
                .stream()
                .map(TacGia::getTenTacGia)
                .collect(Collectors.toList());

        return lstTenTacGia;
    }

    @RequestMapping(value = "/hinhAnhTieuBieu/{maSach}", method = RequestMethod.GET)
    @ResponseBody
    public String getHinhAnhTieuBieu(@PathVariable Long maSach) {
        String hinhAnhTieuBieu = sachRepository
                .findId(maSach)
                .getHinhAnhTieuBieu();

        return hinhAnhTieuBieu == null ? "" : hinhAnhTieuBieu;
    }

    @RequestMapping(value = "/postAnh", method = RequestMethod.POST)
    @ResponseBody
    public String postAnh(MultipartFile[] file) {
        try{
            System.out.println(file);
            String rootPath = servletContext.getRealPath("/");
            String uriPath = "resources" + File.separator + "static" + File.separator + "img";
            String pathResource = rootPath + uriPath;

            for (MultipartFile filePost : file) {
                uploadAnh(filePost, pathResource);
            }

            return "Thanh Cong";
        }catch (Exception e) {
            return "That bai";
        }
    }

    
    private void uploadAnh(MultipartFile file, String pathResource) {
        try {
            File dir = new File(pathResource);
            if (!dir.exists()) {
                dir.mkdir();
            }

            // Create file on server
            File newFile = new File(dir.getAbsoluteFile() + File.separator + file.getOriginalFilename());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(newFile)
            );

            // Ghi file vo thu muc
            bufferedOutputStream.write(file.getBytes());
            bufferedOutputStream.close();
        } catch (IOException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }



    @ModelAttribute("lstSach")
    public List<Sach> lstSach() {
        return
                sachRepository.getAll();
    }
}
