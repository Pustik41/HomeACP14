package classwork.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity
@Table(name = "authors") // table name
public class Author extends IdEntityClass { // class-> table


    @Column(length = 20, nullable = false)
    private String name;
    @Column
    private double salary;
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Transient
    private String secret; // no mapping

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany
    @JoinTable(name = "author_books",
            joinColumns =
                    {@JoinColumn(name = "author_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;

    public Author() {
    }

    public Author(String name, double salary, Date birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", secret='" + secret + '\'' +
                ", address=" + address +
                '}';
    }
}
