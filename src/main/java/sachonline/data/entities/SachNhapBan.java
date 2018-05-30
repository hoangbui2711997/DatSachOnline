package sachonline.data.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SachNhapBan")
public class SachNhapBan {

    @Id
    @GeneratedValue
    private Long maSachNhapBan;

    private Integer soLuong;

    private boolean loaiDon;

    @CreationTimestamp
    private Timestamp ngayXuatKho;

    @ManyToOne
    @JoinColumn(name = "maSach")
    private Sach sachNhapBan;

    @ManyToOne
    @JoinColumn(name = "maHoaDon")
    private HoaDon hoaDon;

    public boolean isLoaiDon() {
        return loaiDon;
    }

    public void setLoaiDon(boolean loaiDon) {
        this.loaiDon = loaiDon;
    }

    @Override
    public String toString() {
        return "SachNhapBan{" +
                "maSachNhapBan=" + maSachNhapBan +
                ", soLuong=" + soLuong +
                ", loaiDon=" + loaiDon +
                ", ngayXuatKho=" + ngayXuatKho +
                ", sachNhapBan=" + sachNhapBan.toString() +
                ", hoaDon=" + hoaDon.toString() +
                '}';
    }

    public SachNhapBan(Integer soLuong, boolean loaiDon, Timestamp ngayXuatKho, Sach sachNhapBan, HoaDon hoaDon) {
        this.soLuong = soLuong;
        this.loaiDon = loaiDon;
        this.ngayXuatKho = ngayXuatKho;
        this.sachNhapBan = sachNhapBan;
        this.hoaDon = hoaDon;
    }

    public SachNhapBan() {
    }

    public Long getMaSachNhapBan() {
        return maSachNhapBan;
    }

    public void setMaSachNhapBan(Long maSachNhapBan) {
        this.maSachNhapBan = maSachNhapBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Timestamp getNgayXuatKho() {
        return ngayXuatKho;
    }

    public void setNgayXuatKho(Timestamp ngayXuatKho) {
        this.ngayXuatKho = ngayXuatKho;
    }

    public Sach getSachNhapBan() {
        return sachNhapBan;
    }

    public void setSachNhapBan(Sach sachNhapBan) {
        this.sachNhapBan = sachNhapBan;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }
}
