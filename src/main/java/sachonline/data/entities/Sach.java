package sachonline.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Sach")
//@NamedQuery(name = "abc", query = "select d from Sach as d")
@Cacheable
public class Sach {

    @Id
    @GeneratedValue
    private Long maSach;

    private int soTrang;
    Date ngayXuatBan;
    private double giaSachBan;
    private double giaSachNhap;
    @Column(columnDefinition = "varchar(50)")
    private String kickThuoc;
    private short tiLeKhuyenMai;
    @Column(columnDefinition = "ntext")
    private String gioiThieu;
    @Column(columnDefinition = "nvarchar(100)")
    private String tenSach;
    @Column(columnDefinition = "nvarchar(100)")
    private String nhaXuatBan;
    @Column(columnDefinition = "nvarchar(100)")
    private String congTyPhatHanh;
    private float danhGia;
    @Column(columnDefinition = "nvarchar(100)")
    private String biaSach;
    @Column(columnDefinition = "ntext")
    private String hinhAnhTieuBieu;

    @Override
    public String toString() {
        return "Sach{" +
                "maSach=" + maSach +
                ", soTrang=" + soTrang +
                ", ngayXuatBan=" + ngayXuatBan +
                ", giaSachBan=" + giaSachBan +
                ", giaSachNhap=" + giaSachNhap +
                ", kickThuoc='" + kickThuoc + '\'' +
                ", tiLeKhuyenMai=" + tiLeKhuyenMai +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", nhaXuatBan='" + nhaXuatBan + '\'' +
                ", congTyPhatHanh='" + congTyPhatHanh + '\'' +
                ", biaSach='" + biaSach + '\'' +
//                ", hinhAnhTieuBieu=" + (hinhAnhTieuBieu == null ? "" : Arrays.asList(hinhAnhTieuBieu)) +
//                ", sachNhapBan=" + sachNhapBan.toString() +
//                ", lstAllTacGia=" + lstAllTacGia.toString() +
                '}';
    }

    public String getBiaSach() {
        return biaSach;
    }

    public void setBiaSach(String biaSach) {
        this.biaSach = biaSach;
    }

    public String getHinhAnhTieuBieu() {
        return hinhAnhTieuBieu;
    }

    public void setHinhAnhTieuBieu(String hinhAnhTieuBieu) {
        this.hinhAnhTieuBieu = hinhAnhTieuBieu;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }

    public Sach(int soTrang, Date ngayXuatBan, double giaSachBan, double giaSachNhap, String kickThuoc, short tiLeKhuyenMai, String gioiThieu, String tenSach, String nhaXuatBan, String congTyPhatHanh, String biaSach, String hinhAnhTieuBieu, List<SachNhapBan> sachNhapBan, List<TacGia> lstTacGia, float danhGia) {
        this.soTrang = soTrang;
        this.ngayXuatBan = ngayXuatBan;
        this.giaSachBan = giaSachBan;
        this.giaSachNhap = giaSachNhap;
        this.kickThuoc = kickThuoc;
        this.tiLeKhuyenMai = tiLeKhuyenMai;
        this.gioiThieu = gioiThieu;
        this.tenSach = tenSach;
        this.nhaXuatBan = nhaXuatBan;
        this.congTyPhatHanh = congTyPhatHanh;
        this.biaSach = biaSach;
        this.hinhAnhTieuBieu = hinhAnhTieuBieu;
        this.sachNhapBan = sachNhapBan;
        this.lstTacGia = lstTacGia;
        this.danhGia = danhGia;
    }

    public String getCongTyPhatHanh() {
        return congTyPhatHanh;
    }

    public void setCongTyPhatHanh(String congTyPhatHanh) {
        this.congTyPhatHanh = congTyPhatHanh;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    @OneToMany(mappedBy = "sachNhapBan", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private List<SachNhapBan> sachNhapBan;

    public List<SachNhapBan> getSachNhapBan() {
        return sachNhapBan;
    }

    public void setSachNhapBan(List<SachNhapBan> sachNhapBan) {
        this.sachNhapBan = sachNhapBan;
    }

    public List<TacGia> getLstTacGia() {
        return lstTacGia;
    }

    public void setLstTacGia(List<TacGia> lstTacGia) {
        this.lstTacGia = lstTacGia;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sach_tacgia",
            joinColumns = {
                    @JoinColumn(name = "maSach")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "maTacGia")
            }
    )
//    @JsonIgnore
    private List<TacGia> lstTacGia;

    public Sach() {
    }

    public void addTacGia(TacGia tacGia) {
        if (lstTacGia == null) {
            lstTacGia = new ArrayList<>();
        }
        lstTacGia.add(tacGia);
    }



    public Long getMaSach() {
        return maSach;
    }

    public void setMaSach(Long maSach) {
        this.maSach = maSach;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public Date getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(Date ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public double getGiaSachBan() {
        return giaSachBan;
    }

    public void setGiaSachBan(double giaSachBan) {
        this.giaSachBan = giaSachBan;
    }

    public double getGiaSachNhap() {
        return giaSachNhap;
    }

    public void setGiaSachNhap(double giaSachNhap) {
        this.giaSachNhap = giaSachNhap;
    }

    public String getKickThuoc() {
        return kickThuoc;
    }

    public void setKickThuoc(String kickThuoc) {
        this.kickThuoc = kickThuoc;
    }

    public short getTiLeKhuyenMai() {
        return tiLeKhuyenMai;
    }

    public void setTiLeKhuyenMai(short tiLeKhuyenMai) {
        this.tiLeKhuyenMai = tiLeKhuyenMai;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getTenSach() {
        return tenSach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sach)) return false;

        Sach sach = (Sach) o;

        if (soTrang != sach.soTrang) return false;
        if (Double.compare(sach.giaSachBan, giaSachBan) != 0) return false;
        if (Double.compare(sach.giaSachNhap, giaSachNhap) != 0) return false;
        if (tiLeKhuyenMai != sach.tiLeKhuyenMai) return false;
        if (Float.compare(sach.danhGia, danhGia) != 0) return false;
        if (maSach != null ? !maSach.equals(sach.maSach) : sach.maSach != null) return false;
        if (ngayXuatBan != null ? !ngayXuatBan.equals(sach.ngayXuatBan) : sach.ngayXuatBan != null) return false;
        if (kickThuoc != null ? !kickThuoc.equals(sach.kickThuoc) : sach.kickThuoc != null) return false;
        if (gioiThieu != null ? !gioiThieu.equals(sach.gioiThieu) : sach.gioiThieu != null) return false;
        if (tenSach != null ? !tenSach.equals(sach.tenSach) : sach.tenSach != null) return false;
        if (nhaXuatBan != null ? !nhaXuatBan.equals(sach.nhaXuatBan) : sach.nhaXuatBan != null) return false;
        if (congTyPhatHanh != null ? !congTyPhatHanh.equals(sach.congTyPhatHanh) : sach.congTyPhatHanh != null)
            return false;
        if (biaSach != null ? !biaSach.equals(sach.biaSach) : sach.biaSach != null) return false;
        if (hinhAnhTieuBieu != null ? !hinhAnhTieuBieu.equals(sach.hinhAnhTieuBieu) : sach.hinhAnhTieuBieu != null)
            return false;
        if (sachNhapBan != null ? !sachNhapBan.equals(sach.sachNhapBan) : sach.sachNhapBan != null) return false;
        return lstTacGia != null ? lstTacGia.equals(sach.lstTacGia) : sach.lstTacGia == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = maSach != null ? maSach.hashCode() : 0;
        result = 31 * result + soTrang;
        result = 31 * result + (ngayXuatBan != null ? ngayXuatBan.hashCode() : 0);
        temp = Double.doubleToLongBits(giaSachBan);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(giaSachNhap);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (kickThuoc != null ? kickThuoc.hashCode() : 0);
        result = 31 * result + (int) tiLeKhuyenMai;
        result = 31 * result + (gioiThieu != null ? gioiThieu.hashCode() : 0);
        result = 31 * result + (tenSach != null ? tenSach.hashCode() : 0);
        result = 31 * result + (nhaXuatBan != null ? nhaXuatBan.hashCode() : 0);
        result = 31 * result + (congTyPhatHanh != null ? congTyPhatHanh.hashCode() : 0);
        result = 31 * result + (danhGia != +0.0f ? Float.floatToIntBits(danhGia) : 0);
        result = 31 * result + (biaSach != null ? biaSach.hashCode() : 0);
        result = 31 * result + (hinhAnhTieuBieu != null ? hinhAnhTieuBieu.hashCode() : 0);
        result = 31 * result + (sachNhapBan != null ? sachNhapBan.hashCode() : 0);
        result = 31 * result + (lstTacGia != null ? lstTacGia.hashCode() : 0);
        return result;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
