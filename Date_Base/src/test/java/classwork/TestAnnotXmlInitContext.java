package classwork;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Котято on 06.11.2016.
 */
public class TestAnnotXmlInitContext {

    @Test
    public  void initContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc/app-xml-annot-context.xml");

    }

}
