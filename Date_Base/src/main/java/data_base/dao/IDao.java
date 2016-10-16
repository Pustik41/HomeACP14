package data_base.dao;

import java.util.List;


public interface IDao<E, K> {

    List<E> getAll();
    boolean create(E obj);
    boolean update(E obj);
    E getObjectById(K id);
    boolean delete(E obj);

}
