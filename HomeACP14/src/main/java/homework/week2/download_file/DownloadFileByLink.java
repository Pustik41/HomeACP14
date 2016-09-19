package homework.week2.download_file;

import homework.week2.download_file.utils.NetDownload;
import homework.week2.download_file.utils.ParserHTML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Котято on 15.09.2016.
 */
public class DownloadFileByLink {

    public static final String DESTENATION_PATH = "E:\\Java\\Projects\\HomeACP14\\src\\main\\resourсes\\home\\week2\\";

    public static void main(String[] args) {
        String link = "http://www.ex.ua/599715401234";
        List<String> loadLinks = new LinkedList<>();

        try {
            ParserHTML parse = new ParserHTML();

            loadLinks.addAll(parse.parserViaCSSSelector(link));
            loadLinks.addAll(5, parse.parserViaRegexp(link));

            int NameFile = 0;

            for (String s : loadLinks) {
                NetDownload.loadLink("http://www.ex.ua" + s, DESTENATION_PATH + NameFile + ".jpg");
                NameFile++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }
}
