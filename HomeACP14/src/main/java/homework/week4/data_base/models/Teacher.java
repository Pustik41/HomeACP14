package homework.week4.data_base.models;

/**
 * Created by Котято on 06.10.2016.
 */
public class Teacher extends Person {

    private String subject;
    private int experience;

    public Teacher(String firstName, String lastName, String mail, int experience, String subject) {
        super(firstName, lastName, mail);
        this.subject = subject;
        this.experience = experience;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Teacher " + super.toString() +
                ", experience " + experience +
                ", subject= " + subject;
    }
}
