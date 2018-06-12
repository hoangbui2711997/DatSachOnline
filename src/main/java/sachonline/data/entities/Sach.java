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
    private String kickThuoc;
    private short tiLeKhuyenMai;
    @Column(name = "gioiThieu", length = 6000)
    private String gioiThieu;
    private String tenSach;
    private String nhaXuatBan, congTyPhatHanh;

    @Column(nullable = true)
    private String biaSach;
    @Column(nullable = true, length = 2000)
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

    public Sach(int soTrang, Date ngayXuatBan, double giaSachBan, double giaSachNhap, String kickThuoc, short tiLeKhuyenMai, String gioiThieu, String tenSach, String nhaXuatBan, String congTyPhatHanh, String biaSach, String hinhAnhTieuBieu, List<SachNhapBan> sachNhapBan, List<TacGia> lstTacGia) {
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

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
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

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
