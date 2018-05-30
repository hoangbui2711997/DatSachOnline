package sachonline.data.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sachonline.data.contract.CrudDao;
import sachonline.data.entities.Sach;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class SachRepository implements CrudDao<Sach> {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Sach> getAll() {
        Query query = entityManager.createNativeQuery("select * from sach", Sach.class);
        List<Sach> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Sach findId(Long id) {
        return entityManager.find(Sach.class, id);
    }

    @Override
    public void insert(Sach newSach) {
        if (newSach.getMaSach() == null) {
            entityManager.persist(newSach);
        } else {
            entityManager.merge(newSach);
        }
    }

    @Override
    public void delete(Long id) {
        Sach sach = entityManager.find(Sach.class, id);
        entityManager.remove(sach);
    }

    @Override
    public void update(Sach sach) {
        entityManager.merge(sach);
    }
}
