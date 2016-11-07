package classwork.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//POJO class + @ID
//@Entity
@Table(name = "books")
public class Book extends IdEntityClass {


    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookType type;

    @Column (nullable = false)
    private String city;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column
    private double price;

    @ManyToMany
    private List<Author> authors;

    public Book() {
    }

    public Book(String bookName, BookType type, String city, Date publishDate, double price) {
        this.bookName = bookName;
        this.type = type;
        this.city = city;
        this.publishDate = publishDate;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", bookName='" + bookName + '\'' +
                ", type=" + type +
                ", city='" + city + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                '}';
    }

    public enum BookType{
        IT, NOVEL, PSYCHOLOGY;
        //0  1       2
    }
}
