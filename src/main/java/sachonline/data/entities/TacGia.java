package sachonline.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TacGia")
public class TacGia {

    @ManyToMany(mappedBy = "lstTacGia")
    @JsonIgnore
    private List<Sach> lstSach;

    @Id
    @GeneratedValue
    private Long maTacGia;
    @Column(columnDefinition = "nvarchar(100) unique")
    private String tenTacGia;



    public TacGia() {

    }

    public void addSach(Sach sach) {
        if(lstSach == null)
            lstSach = new ArrayList<>();
        lstSach.add(sach);
    }

    public Long getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(Long maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public List<Sach> getLstSach() {
        return lstSach;
    }

    public void setLstSach(List<Sach> lstSach) {
        this.lstSach = lstSach;
    }

    public TacGia(String tenTacGia, List<Sach> lstSach) {
        this.tenTacGia = tenTacGia;
        this.lstSach = lstSach;
    }

    @Override
    public String toString() {
        return "TacGia{" +
                "maTacGia=" + maTacGia +
                ", tenTacGia='" + tenTacGia + '\'' +
                '}';
    }
}
