package model;

import exceptions.InvalidInputException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MediaList {
    private Map<String, Media> mediaList;

    public MediaList() {
        mediaList = new HashMap<>();
    }

    //MODIFIES: this
    //EFFECTS: add the movie to the movieList if it isn't already in the list
    public void addToMediaList(Media aMedia) {
        boolean added = false;
        if(!this.mediaList.containsKey(aMedia)) {
            mediaList.put(aMedia.getMediaName(), aMedia);
            aMedia.setList(this);
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

    public void printOutList() {
        AtomicInteger i = new AtomicInteger();
        mediaList.forEach((key, value) -> System.out.println(i.incrementAndGet() + " " + key + " , On Screen Date: " + value.getOnScreenDate() + '\n'));
    }

    public void printOutList(PrintWriter w) {
        mediaList.forEach((key, value) -> w.print(key + " "));
    }

    public void deleteMedia(int deleteNum) {
        Object media = mediaList.keySet().toArray()[deleteNum];
        mediaList.remove(media);
    }

    public void deleteMediaByName(String name) {
        if(name == null || name.equals("")) {
            throw new InvalidInputException("Please enter a media name.");
        }
        Media m = mediaList.get(name);
        mediaList.remove(m);
    }

    public boolean containMedia(Media m) {
        return mediaList.containsKey(m);
    }

}
