// Hyup Kim
// makeyeezy
// Fall 2018

import java.io.*;
import java.net.URL;


public class getImage {

    public static void download(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = url.getFile();
        String destName = "/Users" + fileName.substring(fileName.lastIndexOf("/"));
        System.out.println(destName);

        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destName);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}