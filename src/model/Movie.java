package model;

import javax.swing.*;
import java.util.Date;

public class Movie extends Media{

    //REQUIRES: name and date cannot be null objects
    //MODIFIES: this
    //EFFECTS: initialise movieName and onScreenDate according to the variables that passed in by calling super
    public Movie(String name, String date) {
        super(name, date);
    }

    //EFFECTS: returns the movie's name by calling super
    public String getMovieName() {
        return super.getMediaName();
    }


}


//trying to figure out how to add things like trailer or behindTheScenes
//tosoLater: **********************the onScreenDate should be pull out by using API, need to figure it out later