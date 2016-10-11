package models;


public class Student {

    private String firstName;
    private String lastName;
    private int id;
    private int group_id;
    private String group_name;

    public Student() {
    }

    public Student(String firstName, String lastName, int group_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group_id = group_id;
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

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }



    @Override
    public String toString() {
        return "Student " +
                "firstName= " + firstName +
                ", lastName= " + lastName +
                ", student_id= " + id +
                ", group_id= " + group_id +
                ", group_name='" + group_name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.getClass() == obj.getClass()){
            Student tmp = (Student) obj;

            if(this.firstName.equals(tmp.firstName)){
                if(this.lastName.equals(tmp.lastName)){
                    return this.group_id == tmp.group_id;
                }
            }
        }

        return false;
    }
}
