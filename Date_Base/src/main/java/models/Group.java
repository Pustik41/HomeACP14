package models;


public class Group {

    private String name;
    private int group_id;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Group " +
                "name= " + name +
                ", group_id= " + group_id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.getClass() == obj.getClass()){
            Group tmp = (Group) obj;

            if(this.getName().equals(tmp.getName())){
                return this.getGroup_id() == tmp.getGroup_id();
            }
        }

        return false;
    }
}
