package data_base.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "groups", uniqueConstraints = {@UniqueConstraint(columnNames = {"group_name"})})
public class Group extends IdEntity {

    @Column(name = "group_name")
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupSubjects> groupSubjectses;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> studentList;


    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupSubjects> getGroupSubjectses() {
        return groupSubjectses;
    }

    public void setGroupSubjectses(List<GroupSubjects> groupSubjectses) {
        this.groupSubjectses = groupSubjectses;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Group " +
                "name= " + name +
                ", group_id= " + getId();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.getClass() == obj.getClass()){
            Group tmp = (Group) obj;

            if(this.getName().equals(tmp.getName())){
                return true;
            }
        }

        return false;
    }

}
