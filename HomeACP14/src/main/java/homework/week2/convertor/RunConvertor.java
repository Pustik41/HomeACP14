package homework.week2.convertor;

import homework.week2.convertor.utils.ConvertorImpl;
import homework.week2.convertor.model.Address;
import homework.week2.convertor.model.User;

public class RunConvertor {

    public static void main(String[] args) {

        User user = new User("Sasha", 1, "01-07-1991", "+380383021"
                , new Address("Kiev", "Iskrivska", 4));

        ConvertorImpl convertor = new ConvertorImpl();

        System.out.println(convertor.convertToJson(user));
        System.out.println(convertor.convertToXml(user));
    }
}
