package week8.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Котято on 06.11.2016.
 */
@Component
public class SecondDao implements Dao {
    @Value("second")
    private  String daoInfo;

    public SecondDao() {
    }

    @Override
    public String data() {
        return daoInfo;
    }

    public String getDaoInfo() {
        return daoInfo;
    }

    public void setDaoInfo(String daoInfo) {
        this.daoInfo = daoInfo;
    }
}
