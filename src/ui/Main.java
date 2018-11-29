package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    private static final int FRAME_WIDTH = 850;
    private static final int FRAME_HEIGHT = 1000;
    private static final int START_X = 0;
    private static final int START_Y = 260;
    private String DEFAULT_NAME = " Welcome! ";

    private JPanel panelContainer;
    private MainPage mainPage;
    private SignInPage signInPage;
    private ChooseFavMedia chooseFavMedia;
    private LogOutPage logOutPage;


    public static void main(String[] args){
        ParseFromWeb pfw = new ParseFromWeb();
        upadteDatabase(pfw);
        new Main();
    }

    public Main() {
        Uuup up = new Uuup();
        up.run();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle(DEFAULT_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(START_X, START_Y);

        CardLayout cl = new CardLayout();
        this.panelContainer = new JPanel();
        this.mainPage = new MainPage(cl, this.panelContainer, up);
//        this.signInPage = new SignInPage(cl, this.panelContainer, up);
//        this.chooseFavMedia = new ChooseFavMedia(cl, this.panelContainer, up);
//        this.logOutPage = new LogOutPage(cl, this.panelContainer, up);

        this.panelContainer.setLayout(cl);
        this.panelContainer.add(this.mainPage, "1");
//        this.panelContainer.add(this.signInPage, "2");
//        this.panelContainer.add(this.chooseFavMedia, "3");
//        this.panelContainer.add(this.logOutPage, "4");
        cl.show(panelContainer, "1");

        add(panelContainer);
        setVisible(true);

    }


    public static void upadteDatabase(ParseFromWeb pfw) {
        int mediaNum = 1;
        pfw.readThenCreateMediaFile("MovieURL", "Movie", mediaNum);
        int numOfMovie = pfw.getNumOfMedia();
        pfw.readThenCreateMediaFile("TVShowURL", "TVShow", numOfMovie + 1);
    }
}
