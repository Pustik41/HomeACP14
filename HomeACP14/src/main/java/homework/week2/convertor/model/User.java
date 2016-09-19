package homework.week2.convertor.model;

public class User {

    public String name;
    public int id;
    public String birthDay;
    public String phoneNum;
    public Address address;

    public User(String name, int id, String birthDay, String phoneNum, Address address) {
        this.name = name;
        this.id = id;
        this.birthDay = birthDay;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
