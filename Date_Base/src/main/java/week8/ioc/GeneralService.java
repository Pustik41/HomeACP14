package week8.ioc;

/**
 * Created by Котято on 02.11.2016.
 */
public class GeneralService {

    private GeneralDao dao;

    public GeneralService(GeneralDao dao) {
        this.dao = dao;
    }

    public String doService(){
        return dao.data();
    }
}
