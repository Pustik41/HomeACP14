package week8.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Котято on 02.11.2016.
 */
@Component
public class GeneralService implements Service {

    @Autowired
    @Qualifier("generalDao")
    private Dao dao;

    public GeneralService() {
    }

    public GeneralService(Dao dao) {
        this.dao = dao;
    }

    @Override
    public String doService(){
        return dao.data();
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
