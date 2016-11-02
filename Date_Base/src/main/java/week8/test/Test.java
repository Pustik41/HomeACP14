package week8.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week8.ioc.View;

/**
 * Created by Котято on 02.11.2016.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ioc/app-context.xml");

        View view1 = (View) applicationContext.getBean("view");
        View view2 =  applicationContext.getBean(View.class);
        View view3 =  applicationContext.getBean("view", View.class);

        view1.show();
        view2.show();
        view3.show();
    }
}
