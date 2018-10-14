package model;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class OBMD {

    //get this idea from google

    public JSONObject parsingAPI(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }

    }

    public String getLine(String url) throws IOException {
        JSONObject json = parsingAPI(url);
        String movieName = json.getString("Title");
        String onScreenDate = json.getString("Released");
        return movieName + " ; " + onScreenDate;
    }

    //get this idea from google
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
