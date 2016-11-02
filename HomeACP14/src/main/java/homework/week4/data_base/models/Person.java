package homework.week4.data_base.models;

/**
 * Created by Котято on 06.10.2016.
 */
public abstract class Person  {
    private String firstName;
    private String lastName;
    private String mail;

    public Person(String firstName, String lastName, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "firstName= " + firstName +
                ", lastName= " + lastName  +
                ", mail= " + mail;
    }
}
