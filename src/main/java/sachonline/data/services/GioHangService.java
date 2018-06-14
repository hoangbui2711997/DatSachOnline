package sachonline.data.services;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import sachonline.data.entities.Sach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class GioHangService {

    // Sach va so luong
    private static Map<Sach, Long> gioHang = new HashMap<>();

    public Map<Sach, Long> getAllSach() {
        if (gioHang == null) {
            gioHang = new HashMap<>();
        }
        return gioHang;
    }

    public void addSach(Sach sach, long soLuong) {

        // So luong = 0 thi return
        if(soLuong <= 0) {
            return;
        }
        if (gioHang.containsKey(sach)) {
//            gioHang.
            gioHang.replace(sach, gioHang.get(sach) + soLuong);
        }

        gioHang.put(sach, soLuong);
    }

    public void subtractSach(Sach sach, long soLuong) {
        // So luong = 0 thi return
        if(soLuong <= 0) {
            return;
        }

        // Neu ko co sach nay
        if (!gioHang.containsKey(sach)) {
            return;
        }
        else {
//            gioHang.
            long soLuongConLai = gioHang.get(sach) - soLuong;
            if(soLuongConLai <= 0) {
                gioHang.remove(sach);
                return;
            }

            gioHang.replace(sach, gioHang.get(sach) - soLuong);
        }
    }

    public double thanhTien() {
        if(gioHang == null || gioHang.isEmpty()) {
            return 0;
        } else {
            // Neu muon tra ve List so tien tung cai 1
////            .boxed return stream contain element
//            List<Double> lstGiaTien = gioHang.entrySet()
//                    .stream()
//                    .mapToDouble(sach -> {
////                 lay gia tien * so luong
//                return sach.getKey().getGiaSachBan() * sach.getValue();
//            })
//                    .boxed()
//                    .collect(Collectors.toList());

            double thanhTien = gioHang.entrySet()
                    .stream()
                    .mapToDouble(sach -> {
//                 lay gia tien * so luong
                        double giaBan = sach.getKey().getGiaSachBan();
                        double soLuong = sach.getValue();
                        short tiLeKhuyenMai = sach.getKey().getTiLeKhuyenMai();
                        double tongTienMotLoaiSach = giaBan * soLuong / 100 *  (100 - tiLeKhuyenMai);

                        return tongTienMotLoaiSach;
                    }).sum();

            return thanhTien;
        }
    }

    public String getChiTiet(Sach sach, long soLuong) {
        return "\nTên sách: " + sach.getTenSach() + "\nGiá 1 quyển: "+ sach.getGiaSachBan()
                + "\nSố lượng: " + soLuong;
    }
}
