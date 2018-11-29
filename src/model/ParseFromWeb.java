package model;

import exceptions.MediaNotFoundException;

import java.io.*;
import java.util.Scanner;

public class ParseFromWeb {
    private final String FILE_PATH = "/Users/michauxsun/CPSC210/projectw1_team423/";
    private FileReader fr;
    private PrintWriter writer = null;
    private OMDb OMDb;
    private int numOfMedia;

    //EFFECTS: convert URL into JSON and write the information readed from JSON into a new file
    public void readThenCreateMediaFile(String mediaFile, String mediaType, int mediaNum) {
        numOfMedia = 0;
        try {
            fr = new FileReader(mediaFile);
            BufferedReader br = new BufferedReader(fr);
            if(mediaType.equals("Movie")) {
                writer = new PrintWriter(FILE_PATH + "MovieList1.txt","UTF-8");
            }
            else {
                writer = new PrintWriter(FILE_PATH + "TVShowList1.txt","UTF-8");
            }

            String aLine;

            OMDb = new OMDb();
            while((aLine = br.readLine()) != null) {
                String output = OMDb.getLine(aLine);
                Scanner sc = new Scanner(output);
                String mediaName = sc.next();
                String onScreenDate = sc.nextLine();
                writer.println(mediaNum + " " + mediaName + " " + onScreenDate);
                mediaNum++;
                numOfMedia++;
                sc.close();
            }

        } catch(MediaNotFoundException e) {
            System.out.println("Ops! We don't have this information right now but we are working on it! Come back and try again XD");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();

            }
        }
    }

    public int getNumOfMedia() {
        return numOfMedia;
    }
}
