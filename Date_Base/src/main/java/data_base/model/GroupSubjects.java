package data_base.model;

import javax.persistence.*;

@Entity
@Table(name = "group_subjects")
public class GroupSubjects extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    public GroupSubjects() {
    }

    public GroupSubjects(Integer groupId, Integer subjectId) {
        this.group = new Group();
        this.group.setId(groupId);
        this.subject = new Subject();
        this.subject.setId(subjectId);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "GroupSubjects{" +
                "group=" + group +
                '}';
    }
}
