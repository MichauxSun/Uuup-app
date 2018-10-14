package model;

public class TVShowDatabase extends MediaDataBase {

    public TVShowDatabase(String tvshowList) {
        super(tvshowList);
    }
    @Override
    public Media createMedia(String tvshowName, String onScreenDate) {
        Media aMedia = new TVShow(tvshowName, onScreenDate);
        return aMedia;
    }
}
