package homework.week4.data_base.models;

/**
 * Created by Котято on 06.10.2016.
 */
public class Subject {

    private String subjectName;
    private String description;

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

    @Override
    public String toString() {
        return "Subject " +
                "subjectName= " + subjectName +
                ", description= " + description;
    }

}
