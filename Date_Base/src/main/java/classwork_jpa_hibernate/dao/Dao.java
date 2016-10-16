package classwork_jpa_hibernate.dao;

import classwork_jpa_hibernate.model.Book;

/**
 * Created by Котято on 12.10.2016.
 */
public interface Dao<T> {

    T create(T book);
    boolean delete(T book);
    boolean update(T book);
    T findById(Object id);
}
