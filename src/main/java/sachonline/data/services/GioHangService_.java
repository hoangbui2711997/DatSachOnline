package sachonline.data.services;

import org.springframework.stereotype.Service;
import sachonline.data.entities.Sach;

import java.util.*;

@Service
public class GioHangService_ {
    public List<StoreSupport> getGioHang(HashMap<Sach, Integer> gioHang) {
        List<StoreSupport> list = new ArrayList<>();
        Set<Map.Entry<Sach, Integer>> entries = gioHang.entrySet();

        for (Map.Entry<Sach, Integer> entry : entries) {
            Sach sach = entry.getKey();
            String tenSach = sach.getTenSach();
            short tiLeKhuyenMai = sach.getTiLeKhuyenMai();
            double giaSachBan = sach.getGiaSachBan();
            Integer value = entry.getValue();
            Long maSach = sach.getMaSach();
            StoreSupport storeSupport = new StoreSupport(maSach, tenSach, tiLeKhuyenMai, giaSachBan, value);
            list.add(storeSupport);
        }

        return list;
    }
}
