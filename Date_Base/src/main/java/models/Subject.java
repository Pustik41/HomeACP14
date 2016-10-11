package models;


public class Subject {

    private String subjectName;
    private String description;
    private int subject_id;

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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Subject " +
                "subjectName= " + subjectName +
                ", description= " + description +
                ", subject_id= " + subject_id;
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
