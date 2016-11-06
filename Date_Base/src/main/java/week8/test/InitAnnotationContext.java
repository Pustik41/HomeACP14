package week8.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import week8.ioc.View;

/**
 * Created by Котято on 06.11.2016.
 */
public class InitAnnotationContext {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("week8");
        View view = context.getBean(View.class);
        view.show();

    }
}
