package week8.ioc;

/**
 * Created by Котято on 02.11.2016.
 */
public class View {

    private GeneralService service;



    public  void show() {
        System.out.println(service.doService());
    }

    public GeneralService getService() {
        return service;
    }

    public void setService(GeneralService service) {
        this.service = service;
    }
}
