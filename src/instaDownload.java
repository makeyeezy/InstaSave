// Hyup Kim
// makeyeezy
// Fall 2018


import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.lang.ClassLoader;



public class instaDownload {

    private static String getURLSource(String input) throws IOException, NullPointerException {

        String src = "";
        URL url = new URL(input);
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        BufferedReader bf = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        while (true) {
            String line = bf.readLine();
            if (line ==  null) {
                break;
            }
            src += line;
        }
        return src;

    }

    private static void download(String imageUrl) throws IOException {
        try {
            URL url = new URL(imageUrl);
            String fileName = url.getFile();
            String home = System.getProperty("user.home");
            String destName = home + "/Desktop" + fileName.substring(fileName.lastIndexOf("/"));

            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destName);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (MalformedURLException e) {
            ImageIcon kiki = new ImageIcon(ClassLoader.getSystemResource("3.gif"));
            JOptionPane.showMessageDialog(null,"Private Account! Please try with another URL", "INSTA Downloader", JOptionPane.INFORMATION_MESSAGE,kiki);

        }
    }

    public static void main(String args[]) throws NullPointerException, IOException {

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("1.gif"));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("4.gif"));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("2.gif"));

        Object[] options = {"Video Download", "Picture Download"};
        String o = (String) JOptionPane.showInputDialog(null, "Please Choose the Media Format", "INSTA Downloader", JOptionPane.INFORMATION_MESSAGE, icon2, options, options[0]);

        String input = (String) JOptionPane.showInputDialog(null,"Please Copy and Paste the URL","INSTA Donwnloader",JOptionPane.INFORMATION_MESSAGE,icon,null,null);
        if (input != null) {
            String src = getURLSource(input);
            System.out.println(src);

            if (o.equals("Picture Download")) {
                if (!src.contains("og:image")) {
                    JOptionPane.showMessageDialog(null, "There is only Video in the given URL", "INSTA Downloader", JOptionPane.INFORMATION_MESSAGE, icon3);
                    return;
                }
                String link = org.apache.commons.lang3.StringUtils.substringBetween(src, "og:image\" content=\"", "\" />");
                download(link);
            } else {
                if (!src.contains("og:video")) {
                    JOptionPane.showMessageDialog(null, "There is only Image in the given URL", "INSTA Downloader", JOptionPane.INFORMATION_MESSAGE, icon3);
                    return;
                }
                String link = org.apache.commons.lang3.StringUtils.substringBetween(src, "og:video\" content=\"", "\" />");
                download(link);
            }
        }

    }
}
