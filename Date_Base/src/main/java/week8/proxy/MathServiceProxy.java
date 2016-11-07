package week8.proxy;

/**
 * Created by Котято on 07.11.2016.
 */
public class MathServiceProxy implements MathService {

    private MathService target;

    public MathServiceProxy(MathService target) {
        this.target = target;
    }

    @Override
    public int add(int a, int b) {
        System.out.printf("In mehtod add a = %d, b = %d\n", a, b);
        return target.add(a,b);
    }
}
