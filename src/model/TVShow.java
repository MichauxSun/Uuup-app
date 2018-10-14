package model;

import javax.swing.*;

public class TVShow extends Media {

    //REQUIRES: name and date cannot be null objects
    //MODIFIES: this
    //EFFECTS: initialise TVShowName and onScreenDate according to the variables that passed in by calling super
    public TVShow(String name, String date) {
       super(name, date);
    }

    //EFFECTS: returns the TV Show's name by calling super
    public String getTVShowName() {
        return super.getMediaName();
    }


}


//trying to figure out how to add things like trailer or behindTheScenes
//todoLater: ********* the onScreenDate should be pull out by using API, need to figure it out later