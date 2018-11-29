package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

public class Media extends Observable{
    private String mediaName;
    private String onScreenDate;
    private int mediaType;
    private int numOfFollower;
    private boolean selected;

    private ArrayList<MediaList> mediaLists;
    private MediaPoster poster;

    //MODIFIES: this
    //EFFECTS: initialise Media and onScreenDate according to the variables that passed in
    public Media(String name, String date, int type) {
        this.mediaName = "<<" + name.trim() + ">>";
        this.onScreenDate = date;
        this.mediaType = type;
        this.numOfFollower = 0;
        this.mediaLists = new ArrayList<>();
        this.poster = new MediaPoster(name.trim());
        this.selected = false;

    }

    public JButton getPoster() {
        return this.poster;
    }

    public boolean getSelectionState() {
        return this.selected;
    }

    public void resetSelected() {
        this.selected = !selected;
    }

    public int getType() {
        return this.mediaType;
    }

    //EFFECTS: returns the Media's name
    public String getMediaName() {
        return this.mediaName;
    }

    //EFFECTS: returns the on screen date
    public String getOnScreenDate() {
        return this.onScreenDate;
    }

    public void setList(MediaList list, Person user) {
        if(!this.mediaLists.contains(list)) {
            this.mediaLists.add(list);
            this.numOfFollower++;
            list.addToMediaList(this);
            addObserver(user);
            setChanged();
            notifyObservers(this);
        }
    }

    public int getNumOfFollower() {
        return this.numOfFollower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return mediaType == media.mediaType &&
                Objects.equals(mediaName, media.mediaName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaName);
    }

    public void removeList(MediaList list, Person user) {
        if(this.mediaLists.contains(list)) {
            this.mediaLists.remove(list);
            this.numOfFollower--;
            list.deleteMediaByName(mediaName);
            deleteObserver(user);
        }
    }

//    public void loadImage(ImageIcon poster) {
//        this.mediaPoster = poster;
//    }

}
