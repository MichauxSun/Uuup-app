package model;

public class MovieDatabase extends MediaDataBase {

    public MovieDatabase(String mediaList) {
        super(mediaList);
    }
    @Override
    public Media createMedia(String movieName, String onScreenDate) {
        Media aMedia = new Movie(movieName, onScreenDate);
        return aMedia;
    }
}
