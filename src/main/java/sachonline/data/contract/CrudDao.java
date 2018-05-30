package sachonline.data.contract;

import sachonline.data.entities.TacGia;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAll();

    T findId(Long id);

    void insert(T newT);

    void delete(Long id);

    void update(T tacGia);
}
