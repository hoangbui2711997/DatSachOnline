package sachonline.data.services.adapter;

import sachonline.data.entities.Sach;
import sachonline.data.services.StoreSupport;

public class StoreSupportAdapter {
    public static StoreSupport getStoreSupport(Sach sach) {
        return new StoreSupport(sach.getMaSach(), sach.getTenSach(), sach.getTiLeKhuyenMai(), sach.getGiaSachBan(), 0);
    }
}
