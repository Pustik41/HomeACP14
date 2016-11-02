package classwork_jpa_hibernate.service;

import classwork_jpa_hibernate.model.Book;

import java.util.List;

/**
 * Created by Котято on 02.11.2016.
 */
public interface AuthorService {

    public String login(String name, String pass);

    List<Book> getBooks(int authorId);

    boolean addBook(Book book);
}
