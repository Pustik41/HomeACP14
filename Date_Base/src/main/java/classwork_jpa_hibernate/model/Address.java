package classwork_jpa_hibernate.model;

import javax.persistence.*;
import java.util.List;

//@Entity
@Table (name = "addresses", uniqueConstraints = {@UniqueConstraint(columnNames = {"city", "street"})})
public class Address extends IdEntityClass {

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private  String appNum;

    //mappedBy = fieldName, describe reference
    // between for configure join column
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //cascade указывает какие действия можно делатьс  тобой и с теми кто от тебя зависет
    private List<Author> authorList;                            // то есть когда удалим адресс то все кто к нему привязан в другой таблице тоже удалятся

    public Address() {
    }

    public Address(String city, String street, String appNum) {
        this.city = city;
        this.street = street;
        this.appNum = appNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", appNum='" + appNum + '\'' +
                '}';
    }
}
