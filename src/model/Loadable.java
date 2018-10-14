package model;

import java.util.ArrayList;

public interface Loadable {
    void loadingMedia(String mediaList);
    ArrayList<Media> getDataBase();
}
