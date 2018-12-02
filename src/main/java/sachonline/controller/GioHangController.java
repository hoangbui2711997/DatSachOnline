package sachonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sachonline.data.entities.Sach;
import sachonline.data.services.GioHangService_;
import sachonline.data.services.StoreSupport;
import sachonline.data.services.adapter.StoreSupportAdapter;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/store")
public class GioHangController {

    List<StoreSupport> gioHang = new ArrayList<>();
    @Autowired
    EntityManager repository;
    @Autowired
    GioHangService_ gioHangService_;

//    public void reAssign(HttpSession httpSession) {
//        httpSession.setAttribute("gioHang", this.gioHang);
//    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Collection<StoreSupport> addBookToStore(@RequestParam("id") Long maSach,
                                                   @RequestParam("soLuong") @NotNull int soLuong,
                                                   HttpSession httpSession
    ) {
        assign(httpSession);
        StoreSupport storeSupport = getStoreSupport(maSach);

        // if is exist
        if (storeSupport != null) {
            storeSupport.setSoLuong(soLuong);
        } else {
            Sach sach = repository.find(Sach.class, maSach);
            StoreSupport temp = StoreSupportAdapter.getStoreSupport(sach);
            temp.setSoLuong(soLuong);
            gioHang.add(temp);
        }

        return gioHang;
//        repository.detach(sach);
    }

    private void assign(HttpSession httpSession) {
        this.gioHang = (List<StoreSupport>)
                httpSession.getAttribute("gioHang");
        if (this.gioHang == null) {
            httpSession.setAttribute("gioHang", new ArrayList<StoreSupport>());
            this.gioHang = (List<StoreSupport>)
                    httpSession.getAttribute("gioHang");
        }
    }

    private StoreSupport getStoreSupport(@RequestParam("id") Long maSach) {
        StoreSupport storeSupport = null;
        Stream<StoreSupport> storeSupportStream = gioHang
                .stream()
                .filter(e -> e.getMaSach() == maSach);
        if (storeSupportStream != null) {
            Optional<StoreSupport> first = storeSupportStream
                    .findFirst();
            if (first.isPresent()) {
                storeSupport = first.get();
            }
        }
        return storeSupport;
    }

    @RequestMapping(value = "/getTotal", method = RequestMethod.GET)
    public Double getTotal(HttpSession httpSession) {
        assign(httpSession);
        double total = gioHang.stream().mapToDouble(StoreSupport::getTongTien).sum();
        return total;
    }

    @RequestMapping(value = "/minus", method = RequestMethod.GET)
    public Collection<StoreSupport> minusBook(@RequestParam("id") Long maSach,
                                              Integer amount, HttpSession httpSession) {
        assign(httpSession);
        StoreSupport storeSupport = getStoreSupport(maSach);

        // if is exist
        if (storeSupport != null) {
            int soLuong = storeSupport.getSoLuong();
            if (soLuong > 0) {
                storeSupport.setSoLuong(soLuong - 1);
//                reAssign(httpSession);
            }
        }

        return gioHang;
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Collection<StoreSupport> deleteBook(
            @RequestParam("id") Long maSach,
            HttpSession httpSession) {
        assign(httpSession);

        StoreSupport storeSupport = getStoreSupport(maSach);
        if (storeSupport != null) {
            gioHang.remove(storeSupport);
//            reAssign(httpSession);
        }

        return gioHang;
    }

    @RequestMapping(value = "/getGioHang", method = RequestMethod.GET)
    public Collection<StoreSupport> getGioHang(HttpSession httpSession) {
        assign(httpSession);
        return gioHang;
    }

    @RequestMapping("/{maSach}")
    public Sach getSach(@PathVariable("maSach") Long maSach) {
        Sach sach = repository.find(Sach.class, maSach);
        return sach;
    }
}
