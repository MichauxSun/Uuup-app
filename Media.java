package model;

import java.util.Objects;

public class Media {
    private String mediaName;
    private String onScreenDate;
    private int mediaType;

    private MediaList mediaList;
//    protected ImageIcon mediaPoster;

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

    //MODIFIES: this
    //EFFECTS: initialise Media and onScreenDate according to the variables that passed in
    public Media(String name, String date, int type) {
        this.mediaName = "<<" + name.trim() + ">>";
        this.onScreenDate = date;
        this.mediaType = type;
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

    public void setList(MediaList list) {
        this.mediaList = list;
    }

//    public void loadImage(ImageIcon poster) {
//        this.mediaPoster = poster;
//    }
}
