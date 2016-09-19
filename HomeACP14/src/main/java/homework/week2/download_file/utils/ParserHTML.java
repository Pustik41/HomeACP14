package homework.week2.download_file.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserHTML {

    List<String> listLinks;

    public ParserHTML() {
        listLinks = new LinkedList<>();
    }

    public List parserViaCSSSelector(String url) throws IOException {
        Document document = Jsoup.parse(new URL(url), 1000);
        listLinks = new LinkedList<>();

        Element element = document.body();
        Elements elements = element.select("a[href^=/get]");

        for (Element el : elements) {

            String href = el.attr("href");
            if(href.contains("/get")){
                listLinks.add(href);
            }
        }

        return listLinks;
    }

    public List parserViaXpath(String url){

        return null;
    }

    public List parserViaRegexp(String url) throws IOException {
        listLinks = new LinkedList<>();

        Document document = Jsoup.parse(new URL(url), 1000);
        Element el = document.body();
        Elements elements = el.getElementsByTag("a");

        Pattern p = Pattern.compile("/get/\\w+/\\w+");

        for (Element element : elements) {
            String link = element.toString();
            Matcher m = p.matcher(link);

            while (m.find()) {
                listLinks.add(m.group());
            }
        }

        return listLinks;
    }
}
