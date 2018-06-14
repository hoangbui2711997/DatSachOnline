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

    /**
     * lấy ra trang chủ
     * @return View trang chủ
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "sach/Index";
    }

    /**
     *
     * @param sach đối tượng được tạo
     * @param lstTacgia danh sách tác giả -
     * @return Trả về view quản lý sách nếu tạo thành công, ngược lại về trang tạo sách
     * @throws IOException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String doCreate(
            @ModelAttribute("sach") Sach sach
            ,@RequestParam("lstTacgia") String lstTacgia
    ) throws IOException {
//        String rootPath = System.getProperty("catalina.home");

        // Xử lý List tác giả
        try {
            List<TacGia> lstDaDuocTao = handleAuthors(lstTacgia);
            sach.setLstTacGia(lstDaDuocTao);
            sachRepository.insert(sach);

            return "admin/Sach";
        } catch (Exception e) {
            return "sach/Create";
        }
//        if (!file.isEmpty()) {
//            file.getBytes();
//            String rootPath = System.getProperty("catalina.home");
//            Path path = Files.createFile(Paths.get(""));
//
//        }

    }

    /**
     *
     * @param lstTacgia chuỗi các tác giả bao gồm đã tạo và có thể có chưa được tạo trong Database
     * @return trả về danh sách tác giả được tạo và đã tạo
     */
    private List<TacGia> handleAuthors(String lstTacgia) {
        List<String> authorsName = Arrays.asList(lstTacgia.split(","));
        List<TacGia> authors = tacGiaRepository.getAll();

        List<String> strAuthors = authors
                .stream()
                .map(TacGia::getTenTacGia)
                .collect(Collectors.toList());

        List<String> authorsNeedCreated = null;

        // Tao danh sach chua TacGia da duoc tao roi
        List<TacGia> authorsCreated = null;

        // Lấy danh sách cần đươc tạo
        try {
            authorsNeedCreated = authorsName.stream()
                    .filter(
                            e -> !strAuthors.contains(
                                    e
                            ))
                    .collect(Collectors.toList());


            try {
//                authors.stream()
//                        .filter(
//                                e -> authorsName.contains(
//                                        e.getTenTacGia()
//                                ))
//                        .collect(Collectors.toList()).forEach(e -> System.out.println(e.toString()));
                authorsCreated = authors.stream()
                        .filter(
                                e -> authorsName.contains(
                                        e.getTenTacGia()
                                ))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
                authorsCreated = new ArrayList<>();
            }

            // Tạo danh sách cần được tạo
            for (String tacGia : authorsNeedCreated) {
                TacGia tacGiaX = new TacGia();
                tacGiaX.setTenTacGia(tacGia);
                tacGiaRepository.insert(tacGiaX);

                // Lấy lại tác giả, có thêm id
                TacGia getBackTacGia = tacGiaRepository.getName(
                        tacGiaX.getTenTacGia()
                ).get(0);

                // Add nó vô list được tạo, sau khi kết thúc sẽ cho 1 list đầy đủ
                authorsCreated.add(
                        getBackTacGia
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return authorsCreated;
    }

    /**
     *
     * @param sach rằng buộc đối tượng sách truyền vào - để sử dụng binding trong form spring
     * @return trả về View Create sách
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(@ModelAttribute("sach") Sach sach) {
        return "sach/Create";
    }

    /**
     * lấy chi tiết sách qua mã sách
     * @param model model - giao tiếp giữa controller và view
     * @param maSach mã sách
     * @return lấy View chi tiết của sách thông qua mã sách
     */
    @RequestMapping(value = "/detail/{maSach}", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable Long maSach) {
        Sach sach = sachRepository.findId(maSach);
        if(sach == null) {
            return "error";
        } else {
            model.addAttribute("sach", sach);
            return "sach/Detail";
        }


    }

    /**
     *
     * @param model - giao tiếp giữa controller và view
     * @param maSach mã sách
     * @return
     */
    @RequestMapping(value = "/update/{maSach}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Long maSach) {
        Sach sach = sachRepository.findId(maSach);
        model.addAttribute("sach", sach);

        return "sach/Update";
    }

    /**
     * @param sach rằng buộc đối tượng sách truyền vào - để sử dụng binding trong form spring
     * @param lstTacgia chuỗi các tác giả chưa được cắt
     * @return Trả về view quản lý sách nếu tạo thành công, ngược lại về trang cập nhật sách
     */
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute("sach") Sach sach, @RequestParam("lstTacgia") String lstTacgia) {
//        System.out.println(sach.toString());
        try {
            List<TacGia> lstTGia = handleAuthors(lstTacgia);
            sach.setLstTacGia(lstTGia);
            sachRepository.update(sach);

            return "admin/Sach";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:sach/update/" + sach.getMaSach();
        }
    }

    /**
     *
     * @param maSach
     * @return trả về JSON - danh sách tác giả của cuốn sách có mã sách bằng maSach
     */
    @RequestMapping(value = "/getTacGia/{maSach}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAuthors(@PathVariable Long maSach) {
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
    public String getPresentPictures(@PathVariable Long maSach) {
        String hinhAnhTieuBieu = sachRepository
                .findId(maSach)
                .getHinhAnhTieuBieu();

        return hinhAnhTieuBieu == null ? "" : hinhAnhTieuBieu;
    }

    /**
     * Chịu trách nhiệm lưu ảnh vào web server -
     * @param file tập các file muốn lưu
     * @return trả về Thành công hoặc thất bại - báo cho bên client biết
     */
    @RequestMapping(value = "/postAnh", method = RequestMethod.POST)
    @ResponseBody
    public String postImages(MultipartFile[] file) {
        try{
//            System.out.println(file);
            String rootPath = servletContext.getRealPath("/");
            String uriPath = "resources" + File.separator + "static" + File.separator + "img";
            String pathResource = rootPath + uriPath;

            for (MultipartFile filePost : file) {
                uploadImage(filePost, pathResource);
            }

            return "Thanh Cong";
        }catch (Exception e) {
            return "That bai";
        }
    }

    /**
     *
     * @return Lấy tất cả sách
     */
    @RequestMapping(value = "/getSach", method = RequestMethod.GET)
    @ResponseBody
    public List<Sach> getBooks() {
        List<Sach> lstSach = sachRepository.getAll();
        lstSach.forEach(e -> System.out.println(e.getLstTacGia().size()));
        return
                lstSach;
    }

    /**
     * Xử lý lưu ảnh
     * @param file file muốn lưu
     * @param pathResource đường dẫn lưu ảnh
     */
    private void uploadImage(MultipartFile file, String pathResource) {
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
}
