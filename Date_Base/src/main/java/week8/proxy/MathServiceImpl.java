package week8.proxy;

/**
 * Created by Котято on 07.11.2016.
 */


public class MathServiceImpl implements MathService {

    @Override
    public int add(int a, int b){
        return a + b;
    }
}
