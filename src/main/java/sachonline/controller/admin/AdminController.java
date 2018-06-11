package sachonline.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sachonline.data.entities.Sach;
import sachonline.data.repositories.SachRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    SachRepository sachRepository;

    @RequestMapping(value = "/sach", method = RequestMethod.GET)
    public String getBooks(Model model) {
        model.addAttribute("books" ,sachRepository.getAll());
        return "admin/Sach";
    }

    @RequestMapping(value = "/getSach", method = RequestMethod.GET)
    @ResponseBody
    public List<Sach> lstSach() {
        return
                sachRepository.getAll();
    }

    @RequestMapping(value = "/doDeleteSach/{maSach}", method = RequestMethod.GET)
    @ResponseBody
    public String doDelete(@PathVariable("maSach") Long maSach) {
        try {
            sachRepository.delete(maSach);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}
