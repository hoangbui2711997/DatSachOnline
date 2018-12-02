package sachonline.data.entities;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @GeneratedValue
    private Long maHoaDon;
    private double thanhTien;

    @ManyToOne
    @JoinColumn(name = "maDiaChi")
    private DiaChi diaChi;

    @Column(columnDefinition = "varchar(15)")
    private String dienThoai;
    private Timestamp ngayGiaoToi;

    @CreationTimestamp
    private Timestamp ngayDat;
    @Column(columnDefinition = "nvarchar(100)")
    private String tenNguoiNhan;
    @Column(columnDefinition = "ntext")
    private String moTa;

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon=" + maHoaDon +
                ", thanhTien=" + thanhTien +
                ", diaChi=" + diaChi.toString() +
                ", dienThoai='" + dienThoai + '\'' +
                ", ngayGiaoToi=" + ngayGiaoToi +
                ", ngayDat=" + ngayDat +
                ", tenNguoiNhan='" + tenNguoiNhan + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }

    public Long getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Long maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public Timestamp getNgayGiaoToi() {
        return ngayGiaoToi;
    }

    public void setNgayGiaoToi(Timestamp ngayGiaoToi) {
        this.ngayGiaoToi = ngayGiaoToi;
    }

    public Timestamp getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Timestamp ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public HoaDon(String moTa, double thanhTien, DiaChi diaChi, String dienThoai, Timestamp ngayGiaoToi, Timestamp ngayDat, String tenNguoiNhan) {
        this.moTa = moTa;
        this.thanhTien = thanhTien;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.ngayGiaoToi = ngayGiaoToi;
        this.ngayDat = ngayDat;
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public HoaDon() {
    }
}
