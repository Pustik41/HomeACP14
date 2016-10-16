package data_base.model;

import javax.persistence.*;

@Entity
@Table(name = "student_progress")
public class StudentProgress extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @Column
    private int mark;

    public StudentProgress() {
    }

    public StudentProgress(Student student, Subject subject, int mark) {
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "StudentProgress " +
                student +
                subject +
                ", mark=";
    }
}
