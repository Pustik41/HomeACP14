package week8.proxy;

/**
 * Created by Котято on 07.11.2016.
 */
public class MathServiceFactory {
    public static MathService create() {
        return new MathServiceProxy(new MathServiceImpl());
    }
}
