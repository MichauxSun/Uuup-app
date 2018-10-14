package model;

import javax.swing.*;

public class Media {
    private String mediaName;
    private String onScreenDate;
    protected ImageIcon mediaPoster;

    //REQUIRES: name and date cannot be null objects
    //MODIFIES: this
    //EFFECTS: initialise Media and onScreenDate according to the variables that passed in
    public Media(String name, String date) {
        this.mediaName = "<<" + name.trim() + ">>";
        this.onScreenDate = date;
    }

    //EFFECTS: returns the Media's name
    public String getMediaName() {
        return this.mediaName;
    }

    //EFFECTS: returns the on screen date
    public String getOnScreenDate() {
        return this.onScreenDate;
    }

    public void loadImage(ImageIcon poster) {
        this.mediaPoster = poster;
    }
}
