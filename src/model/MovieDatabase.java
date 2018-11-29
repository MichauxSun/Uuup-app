package model;

public class MovieDatabase extends MediaDataBase {
    private final int MEDIA_TYPE = 1;

    public MovieDatabase(String mediaList) {
        super(mediaList);
    }
    @Override
    public Media createMedia(String movieName, String onScreenDate) {
        Media aMedia = new Movie(movieName, onScreenDate, MEDIA_TYPE);
        return aMedia;
    }
}
