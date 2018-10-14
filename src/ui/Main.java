package ui;

import model.*;

public class Main {
    public static void main(String[] args){
        ParseFromWeb pfw = new ParseFromWeb();
        upadteDatabase(pfw);
        Uuup up = new Uuup();
        up.run();

    }

    public static void upadteDatabase(ParseFromWeb pfw) {
        int mediaNum = 1;
        int numOfMovie = pfw.readThenCreateMediaFile("MovieURL", "Movie", mediaNum);
        int numOfTVShow = pfw.readThenCreateMediaFile("TVShowURL", "TVShow", numOfMovie + 1);
    }
}
