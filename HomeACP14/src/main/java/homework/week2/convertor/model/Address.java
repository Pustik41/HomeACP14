package homework.week2.convertor.model;

public class Address {

    public String city;
    public String street;
    public int numOfHouse;

    public Address(String city, String street, int numOfHouse) {
        this.city = city;
        this.street = street;
        this.numOfHouse = numOfHouse;
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

    public int getNumOfHouse() {
        return numOfHouse;
    }

    public void setNumOfHouse(int numOfHouse) {
        this.numOfHouse = numOfHouse;
    }

    @Override
    public String toString() {
        return
                "city " + city +
                " street " + street +
                " numOfHouse "+ numOfHouse ;
    }
}
