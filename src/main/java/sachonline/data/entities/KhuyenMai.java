package sachonline.data.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "KhuyenMai")
public class KhuyenMai {

    @CreationTimestamp
    private Timestamp ngayBatDau;

    private Timestamp ngayKetThuc;
    @Column(columnDefinition = "ntext")
    private String moTa;

    @Id
    @GeneratedValue
    private Long maKhuyenMai;

    public KhuyenMai(Timestamp ngayBatDau, Timestamp ngayKetThuc, String moTa) {
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.moTa = moTa;
    }

    public KhuyenMai() {
    }

    public Timestamp getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Timestamp ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Timestamp getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Timestamp ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Long getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(Long maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

}
