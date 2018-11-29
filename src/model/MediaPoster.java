package model;

import javax.swing.*;

public class MediaPoster extends JButton{
    private final String IMAGE_DIRECTORY = "/..//Users/michauxsun/CPSC210/projectw1_team423/Media Poster/";
    private String posterName;

    public MediaPoster(String name) {
        super();
        this.posterName = name.trim();
        setIcon(new ImageIcon(IMAGE_DIRECTORY + posterName + ".jpg"));
    }

    public String getPosterName() {
        return this.posterName;
    }
}
