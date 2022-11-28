package Repository;

import java.util.List;

public interface ICrudRepository<ID, E> {
    boolean add(E e);
    boolean delete(ID id);
    boolean update(ID id, E e);
    E findbyId(ID id);
    List<E> getAll();

}
