package model;

import exceptions.InvalidInputException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MediaList{
    protected HashMap<String, Media> mediaList;
    private Person user;
    private int mediaType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaList mediaList = (MediaList) o;
        return Objects.equals(user, mediaList.user) && mediaType == mediaList.mediaType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(user);
    }

    public MediaList(Person user) {
        mediaList = new HashMap<>();
        this.user = user;
        mediaType = -1;
    }

    public HashMap<String, Media> getMediaList() {
        return mediaList;
    }

    //MODIFIES: this
    //EFFECTS: add the movie to the movieList if it isn't already in the list
    public void addToMediaList(Media aMedia) {
        boolean added = false;
        if (aMedia.getType() != mediaType && mediaType != -1) {
            return;
        }
        if(!this.mediaList.containsKey(aMedia)) {
            this.mediaType = aMedia.getType();
            mediaList.put(aMedia.getMediaName(), aMedia);

            aMedia.setList(this, user);
            aMedia.resetSelected();
            added = true;
        }
        if(!added) {
            String mediaType = "";
            if(aMedia.getType() == 1) {
                mediaType = "Moive";
            }
            else if(aMedia.getType() == 2){
                mediaType = "TVShow";
            }
            System.out.println("The " + mediaType + ": " + aMedia.getMediaName() + " is already in your list.");
        }
    }

    public int getSize() {
        return mediaList.size();
    }

    public String printOutList() {
        String list = "";
        int i = 0;
        for(String s: mediaList.keySet()) {
            i++;
            list += i + " " + s + '\n';
        }
        return list;
    }

    public void printOutList(PrintWriter w) {
        mediaList.forEach((key, value) -> w.print(key + " "));
    }

    public void deleteMedia(int deleteNum) {
        Object media = mediaList.keySet().toArray()[deleteNum];
        String mediaName = (String)media;
        deleteMediaByName(mediaName);

    }

    public void deleteMediaByName(String name) {
        if(name == null || name.equals("")) {
            throw new InvalidInputException("Please enter a media name.");
        }
        Media m = mediaList.get(name);
        this.mediaList.remove("<<"+name+">>");
//        m.resetSelected();
//        m.removeList(this, user);

    }

    public boolean containMedia(String m) {
        return mediaList.containsKey(m);
    }

}