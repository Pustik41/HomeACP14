package data_base.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects", uniqueConstraints = {@UniqueConstraint(columnNames = {"subject_name"})})
public class Subject extends IdEntity {

    @Column(name = "subject_name")
    private String subjectName;

    @Column
    private String description;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<GroupSubjects> groupSubjectses;

    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    private Teacher teacher;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<StudentProgress> studentProgresses;

    public Subject() {
    }

    public Subject(String subjectName, String description) {
        this.subjectName = subjectName;
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GroupSubjects> getGroupSubjectses() {
        return groupSubjectses;
    }

    public void setGroupSubjectses(List<GroupSubjects> groupSubjectses) {
        this.groupSubjectses = groupSubjectses;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<StudentProgress> getStudentProgresses() {
        return studentProgresses;
    }

    public void setStudentProgresses(List<StudentProgress> studentProgresses) {
        this.studentProgresses = studentProgresses;
    }

    @Override
    public String toString() {
        return "Subject " +
                "subjectName= " + subjectName +
                ", description= " + description +
                ", subject_id= " + getId();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.getClass() == obj.getClass()){
            Subject tmp = (Subject) obj;

            if(this.getSubjectName().equals(tmp.getSubjectName())){
                return true;
            }
        }

        return false;
    }

}
