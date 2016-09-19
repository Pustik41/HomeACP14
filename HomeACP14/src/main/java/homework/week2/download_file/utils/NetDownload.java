package homework.week2.download_file.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.Buffer;

/**
 * Created by Котято on 15.09.2016.
 */
public class NetDownload {

    public static void loadLink(String link, String destination)
            throws URISyntaxException, IOException {

        try(InputStream is = new URI(link).toURL().openStream();
            OutputStream os = new FileOutputStream(destination);) {

            byte[] buffers = new byte[1024];
            int count = 0;

            while ((count = is.read(buffers)) != -1){
                os.write(buffers, 0, count);
                os.flush();
            }

            os.close();
        }
    }
}
