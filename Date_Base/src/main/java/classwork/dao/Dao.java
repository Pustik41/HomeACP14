package classwork.dao;

/**
 * Created by Котято on 12.10.2016.
 */
public interface Dao<T> {

    T create(T book);
    boolean delete(T book);
    boolean update(T book);
    T findById(Object id);
}
