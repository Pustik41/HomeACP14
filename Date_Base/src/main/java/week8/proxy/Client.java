package week8.proxy;

/**
 * Created by Котято on 07.11.2016.
 */
public class Client {

    public static void main(String[] args) {
        MathService mathService = MathServiceFactory.create();
        System.out.println(mathService.add(23, 45));
    }
}
