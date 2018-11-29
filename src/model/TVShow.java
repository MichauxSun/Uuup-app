package model;

import javax.swing.*;

public class TVShow extends Media {

    //MODIFIES: this
    //EFFECTS: initialise TVShowName and onScreenDate according to the variables that passed in by calling super
    public TVShow(String name, String date, int type) {
       super(name, date, type);
    }

    //EFFECTS: returns the TV Show's name by calling super
    public String getTVShowName() {
        return super.getMediaName();
    }


}
