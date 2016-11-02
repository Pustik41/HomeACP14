package homework.week4.data_base.models;

/**
 * Created by Котято on 06.10.2016.
 */
public class Group {
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group " +
                "name= " + name;
    }
}
