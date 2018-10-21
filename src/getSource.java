// Hyup Kim
// makeyeezy
// Fall 2018

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getSource {

    public static String getURLSource(String input) throws IOException {

        String src = "";
        URL url = new URL(input);
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        BufferedReader bf = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        while (bf.readLine() != null) {
            src += bf.readLine();
        }
        return src;

    }
}
