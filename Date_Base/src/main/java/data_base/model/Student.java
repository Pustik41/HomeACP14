package data_base.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends IdEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentProgress> studentProgresses;


    public Student() {
    }

    public Student(String firstName, String lastName, int groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = new Group();
        this.group.setId(groupId);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<StudentProgress> getStudentProgresses() {
        return studentProgresses;
    }

    public void setStudentProgresses(List<StudentProgress> studentProgresses) {
        this.studentProgresses = studentProgresses;
    }

    @Override
    public String toString() {
        return "Student " +
                "firstName= " + firstName +
                ", lastName= " + lastName +
                ", student_id= " + getId() +
                ", group= " + group;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.getClass() == obj.getClass()){
            Student tmp = (Student) obj;

            if(this.firstName.equals(tmp.firstName)){
                if(this.lastName.equals(tmp.lastName)){
                    return this.group.getId() == tmp.group.getId();
                }
            }
        }

        return false;
    }
}
