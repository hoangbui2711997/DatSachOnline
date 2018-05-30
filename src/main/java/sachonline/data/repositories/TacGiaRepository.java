package sachonline.data.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sachonline.data.contract.CrudDao;
import sachonline.data.entities.TacGia;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class TacGiaRepository implements CrudDao<TacGia> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<TacGia> getAll() {
        Query query = entityManager.createNativeQuery("select * from tac_gia", TacGia.class);
        List<TacGia> resultList = query.getResultList();
        return resultList ;
    }

    @Override
    public TacGia findId(Long id) {
        return entityManager.find(TacGia.class, id);
    }

    @Override
    public void insert(TacGia newTacGia) {
        if (newTacGia.getMaTacGia() == null) {
            entityManager.persist(newTacGia);
        } else {
            entityManager.merge(newTacGia);
        }
    }

    @Override
    public void delete(Long id) {
        TacGia tacGia = entityManager.find(TacGia.class, id);
        entityManager.remove(tacGia);
    }

    @Override
    public void update(TacGia tacGia) {
        entityManager.merge(tacGia);
    }

    public List<TacGia>  getName(String name) {
        List<TacGia> lstTacGia = entityManager
                .createNativeQuery(
                        "select * from tac_gia " +
                        "where ten_tac_gia = '" + name + "'",
                        TacGia.class)
                .getResultList();
        return lstTacGia;
    }
}
