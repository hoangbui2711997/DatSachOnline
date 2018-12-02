package sachonline.data.services;

public class StoreSupport {
    private String tenSach;
    private short khuyenMai;
    private double gia;
    private int soLuong;
    private double tongTien;
    private Long maSach;

    public Long getMaSach() {
        return maSach;
    }

    public void setMaSach(Long maSach) {
        this.maSach = maSach;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoreSupport)) return false;

        StoreSupport that = (StoreSupport) o;

        if (khuyenMai != that.khuyenMai) return false;
        if (Double.compare(that.gia, gia) != 0) return false;
        if (tenSach != null ? !tenSach.equals(that.tenSach) : that.tenSach != null) return false;
        return maSach != null ? maSach.equals(that.maSach) : that.maSach == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = tenSach != null ? tenSach.hashCode() : 0;
        result = 31 * result + (int) khuyenMai;
        temp = Double.doubleToLongBits(gia);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (maSach != null ? maSach.hashCode() : 0);
        return result;
    }

    public StoreSupport(Long maSach, String tenSach, short khuyenMai, double gia, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.khuyenMai = khuyenMai;
        this.gia = gia;
        this.soLuong = soLuong;
        double tienKhuyenMai = gia * (float) (khuyenMai / 100);
        this.tongTien = soLuong * (gia - tienKhuyenMai);
    }

    @Override
    public String toString() {
        return "StoreSupport{" +
                "tenSach='" + tenSach + '\'' +
                ", khuyenMai=" + khuyenMai +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                '}';
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public short getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(short khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        // update tong tien
        double tienKhuyenMai = gia * (float) (khuyenMai / 100);
        this.tongTien = soLuong * (gia - tienKhuyenMai);
    }
}
