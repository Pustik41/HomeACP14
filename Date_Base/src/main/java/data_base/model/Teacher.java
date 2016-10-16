package data_base.model;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends IdEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int experience;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, int subject_id, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.subject = new Subject();
        this.subject.setId(subject_id);
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher " +
                "firstName= " + firstName  +
                ", lastName= " + lastName +
                ", teacher_id=" + getId() +
                ", experience=" + experience +
                ", subject=" + subject;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.getClass() == obj.getClass()){
            Teacher tmp = (Teacher) obj;

            if(this.firstName.equals(tmp.firstName)){
                if(this.lastName.equals(tmp.lastName)){
                    return this.experience == tmp.experience;
                }
            }
        }

        return false;
    }
}
