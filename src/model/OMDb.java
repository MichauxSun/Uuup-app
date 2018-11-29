package model;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class OMDb {
    private final String URL_PATH = "http://www.omdbapi.com/?apikey=220a58bc&t=";
    //get this idea from google

    public String getLine(String mediaName) throws IOException { //can either put parent Exception in the signature or only put specific child exception

        JSONObject json = parsingAPI(mediaName);
        String movieName = json.getString("Title");
        String onScreenDate = json.getString("Released");
        return movieName + " ; " + onScreenDate;
    }

    public JSONObject parsingAPI(String mediaName) throws IOException {
        InputStream is = new URL(URL_PATH + mediaName).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }

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
