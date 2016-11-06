package week8.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Котято on 02.11.2016.
 */
@Component
public class View {

    @Autowired
    private Service service;

    public  void show() {
        System.out.println(service.doService());
    }

    public Service getService() {
        return service;
    }

    public void setService(GeneralService service) {
        this.service = service;
    }
}
