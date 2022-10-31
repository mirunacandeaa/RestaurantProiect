package Repository;

public interface ICrudRepository<ID, E> {
    boolean add(E e);
    boolean delete(ID id);
    boolean update(ID id, E e);
    E findbyId(ID id);

}
