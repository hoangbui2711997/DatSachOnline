package sachonline.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "test_test", query = "select d from DiaChi d")
@Table(name = "DiaChi")
public class DiaChi {

    private String loaiDiaChi;//nha rieng hay cong ty,...

    @Id
    @GeneratedValue
    private Long maDiaChi;
    @Column(columnDefinition = "nvarchar(100)")
    private String phuongXa;
    @Column(columnDefinition = "nvarchar(100)")
    private String quanHuyen;
    @Column(columnDefinition = "nvarchar(100)")
    private String tinhThanhPho;
    @Column(columnDefinition = "nvarchar(100)")
    private String chiTietDiaChi;

    @Override
    public String toString() {
        return "DiaChi{" +
                "loaiDiaChi='" + loaiDiaChi + '\'' +
                ", maDiaChi=" + maDiaChi +
                ", phuongXa='" + phuongXa + '\'' +
                ", quanHuyen='" + quanHuyen + '\'' +
                ", tinhThanhPho='" + tinhThanhPho + '\'' +
                ", chiTietDiaChi='" + chiTietDiaChi + '\'' +
                '}';
    }

    public DiaChi(String loaiDiaChi, String phuongXa, String quanHuyen, String tinhThanhPho, String chiTietDiaChi, List<HoaDon> lstHoaDon) {
        this.loaiDiaChi = loaiDiaChi;
        this.phuongXa = phuongXa;
        this.quanHuyen = quanHuyen;
        this.tinhThanhPho = tinhThanhPho;
        this.chiTietDiaChi = chiTietDiaChi;
        this.lstHoaDon = lstHoaDon;
    }

    public String getChiTietDiaChi() {
        return chiTietDiaChi;
    }

    public void setChiTietDiaChi(String chiTietDiaChi) {
        this.chiTietDiaChi = chiTietDiaChi;
    }

    public void setLstHoaDon(List<HoaDon> lstHoaDon) {
        this.lstHoaDon = lstHoaDon;
    }

    @OneToMany(mappedBy = "diaChi", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private List<HoaDon> lstHoaDon;

    public DiaChi() {
    }

    public String getLoaiDiaChi() {
        return loaiDiaChi;
    }

    public void setLoaiDiaChi(String loaiDiaChi) {
        this.loaiDiaChi = loaiDiaChi;
    }

    public Long getMaDiaChi() {
        return maDiaChi;
    }

    public void setMaDiaChi(Long maDiaChi) {
        this.maDiaChi = maDiaChi;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getTinhThanhPho() {
        return tinhThanhPho;
    }

    public void setTinhThanhPho(String tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }

    public List<HoaDon> getLstHoaDon() {
        return this.lstHoaDon;
    }

    public void setHoaDon(List<HoaDon> lstHoaDon) {
        this.lstHoaDon = lstHoaDon;
    }
}
