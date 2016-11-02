package week8.ioc;

/**
 * Created by Котято on 02.11.2016.
 */
public class GeneralDao {

    private String name;
    private int count;
    private double limit;

    public GeneralDao() {
    }

    public String data() {
        return name + " " + count + " " + limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
