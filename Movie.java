package model;

import javax.swing.*;
import java.util.Date;

public class Movie extends Media{

    //MODIFIES: this
    //EFFECTS: initialise movieName and onScreenDate according to the variables that passed in by calling super
    public Movie(String name, String date, int type) {
        super(name, date, type);
    }

    //EFFECTS: returns the movie's name by calling super
    public String getMovieName() {
        return super.getMediaName();
    }


}
