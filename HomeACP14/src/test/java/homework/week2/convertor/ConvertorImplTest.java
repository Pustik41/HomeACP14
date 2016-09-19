package homework.week2.convertor;

import com.google.gson.Gson;
import homework.week2.convertor.model.Address;
import homework.week2.convertor.model.User;
import homework.week2.convertor.utils.ConvertorImpl;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXB;

import static org.junit.Assert.*;

/**
 * Created by Котято on 16.09.2016.
 */
public class ConvertorImplTest {

    User user;
    ConvertorImpl convertor;
    String forXML;

    @Before
    public void setUp() throws Exception {
        user = new User("Sasha", 1, "01-07-1991", "+3806574", new Address("Riga", "Ierocu", 4));
        forXML = "<team>\n" +
                "\t<name>Sasha</name>\n" +
                "\t<id>1</id>\n" +
                "\t<birthDay>01-07-1991</birthDay>\n" +
                "\t<phoneNum>+3806574</phoneNum>\n" +
                "\t<address>\n" +
                "\t\t<city>Riga</city>\n" +
                "\t\t<street>Ierocu</street>\n" +
                "\t\t<numOfHouse>4</numOfHouse>\n" +
                "\t</address>\n" +
                "</team>";

        convertor = new ConvertorImpl();
    }

    @Test
    public void convertToJson() {
        String expected = new Gson().toJson(user);
        String actual = convertor.convertToJson(user);
        assertEquals(expected, actual);
    }


    @Test
    public void convertToXml() throws Exception {
        assertEquals(forXML, convertor.convertToXml(user));
    }

}