package models;


public class Teacher {

    private String firstName;
    private String lastName;
    private int id;
    private int subject_id;
    private int experience;
    private String subject_name;

    public Teacher(String firstName, String lastName, int subject_id, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject_id = subject_id;
        this.experience = experience;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public String toString() {
        return "Teacher " +
                "firstName= " + firstName  +
                ", lastName= " + lastName +
                ", teacher_id=" + id +
                ", experience=" + experience +
                ", subject_id=" + subject_id +
                ", subject_name= " + subject_name;
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
