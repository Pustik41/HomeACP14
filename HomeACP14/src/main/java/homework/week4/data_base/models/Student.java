package homework.week4.data_base.models;

/**
 * Created by Котято on 06.10.2016.
 */
public class Student extends Person {

    private String group;

    public Student(String firstName, String lastName, String mail, String group) {
        super(firstName, lastName, mail);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {

        return "Student " + super.toString() +
                "group= " + group;
    }
}
