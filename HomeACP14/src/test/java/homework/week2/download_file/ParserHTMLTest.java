package homework.week2.download_file;

import homework.week2.download_file.utils.ParserHTML;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Котято on 16.09.2016.
 */
public class ParserHTMLTest {

    String link;
    ParserHTML parserHTML;
    List<String> loadLinks;

    @Before
    public void setUp() throws Exception {
        link = "http://www.ex.ua/599715401234";
        parserHTML = new ParserHTML();
        loadLinks = new LinkedList<>();
        Collections.addAll(loadLinks, "/get/599715401234/272671081",
                "/get/599715401234/272671084",
                "/get/599715401234/272671088",
                "/get/599715401234/272671091",
                "/get/599715401234/272671092");

    }

    @After
    public void tearDown() throws Exception {
        loadLinks.clear();
    }

    @Test
    public void parserViaCSSSelector() throws IOException {
        assertEquals(loadLinks, parserHTML.parserViaCSSSelector(link));
    }

    @Test(expected =  MalformedURLException.class)
    public void parserViaCSSSelector_wrong_link() throws IOException {
        link = "sds";
        assertEquals(loadLinks, parserHTML.parserViaCSSSelector(link));
    }

    @Test
    public void parserViaRegexp() throws IOException {
        assertEquals(loadLinks, parserHTML.parserViaRegexp(link));
    }

    @Test(expected =  MalformedURLException.class)
    public void parserViaRegexp_wrong_link() throws IOException {
        link = "ssd";
        assertEquals(loadLinks, parserHTML.parserViaRegexp(link));
    }

}