package model;

public class TVShowDatabase extends MediaDataBase {
    private final int MEDIA_TYPE = 2;

    public TVShowDatabase(String tvshowList) {
        super(tvshowList);
    }
    @Override
    public Media createMedia(String tvshowName, String onScreenDate) {
        Media aMedia = new TVShow(tvshowName, onScreenDate, MEDIA_TYPE);
        return aMedia;
    }
}
