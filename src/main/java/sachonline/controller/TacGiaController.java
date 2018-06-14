package sachonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sachonline.data.entities.TacGia;
import sachonline.data.repositories.TacGiaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tacgia")
@CrossOrigin(origins = "localhost:8080/.*")
public class TacGiaController {

    @Autowired
    TacGiaRepository tacGiaRepository;

    /**
     * lấy hết tên của tác giả và đưa vào list - gọi qua ajax
     * @return
     */
    @RequestMapping("/getall")
    @ResponseBody
    public List<String> getAll() {

        List<String> lstNameTacGia = tacGiaRepository
                .getAll()
                .stream()
                .map(TacGia::getTenTacGia)
                .collect(Collectors.toList());

        return lstNameTacGia;
    }
}
