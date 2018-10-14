package model;

import java.io.*;
import java.util.Scanner;

public class ParseFromWeb {

    //EFFECTS: convert URL into JSON and write the information readed from JSON into a new file
    public int readThenCreateMediaFile(String mediaFile, String mediaType, int mediaNum) {
        FileReader fr;
        PrintWriter writer = null;
        int count = 0;
        try {
            fr = new FileReader(mediaFile);
            BufferedReader br = new BufferedReader(fr);
            if(mediaType.equals("Movie")) {
                writer = new PrintWriter("/Users/michauxsun/CPSC210/projectw1_team423/MovieList1.txt","UTF-8");
            }
            else {
                writer = new PrintWriter("/Users/michauxsun/CPSC210/projectw1_team423/TVShowList1.txt","UTF-8");
            }

            String aLine;

            OBMD obmd = new OBMD();
            while((aLine = br.readLine()) != null) {
                String output = obmd.getLine(aLine);
                Scanner sc = new Scanner(output);
                String mediaName = sc.next();
                String onScreenDate = sc.nextLine();
                writer.println(mediaNum + " " + mediaName + " " + onScreenDate);
                mediaNum++;
                count++;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if ( writer != null ) {
                writer.close();
            }
        }
        return count;

    }
}
