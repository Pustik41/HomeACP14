package controller;

import java.util.List;


public interface IController <E, K> {

    List<E> getAll();
    boolean create(E obj);
    boolean update(E obj);
    E getObjectById(K id);
    boolean delete(E obj);

}
