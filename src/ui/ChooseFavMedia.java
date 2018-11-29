package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class ChooseFavMedia extends JPanel {
    private JButton[] posters = new JButton[10];
    private JPanel upperPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private Media media;
    private CardLayout cl;
    private JPanel panelContainer;

    private JButton messageBoard;
    private static Font messageFont = new Font("Monospaced", Font.BOLD, 20);
    private final String IMAGE_DIRECTORY = "/..//Users/michauxsun/CPSC210/projectw1_team423/Media Poster/";
    private SelectedListener selectedListener;
    protected Uuup up;

    public ChooseFavMedia(CardLayout cl, JPanel panelContainer, Uuup up) {
        this.up = up;
        this.cl = cl;
        this.panelContainer = panelContainer;
        int movieSize = up.getMovieDatabaseSize();
        int tvshowSize = up.getTVShowDatabaseSize();
        selectedListener = new SelectedListener();

        upperPanel = buildUpperDatabasePanel();
        centerPanel = buildCenterDatabasePanel(movieSize, tvshowSize, up);
        bottomPanel = buildBottomPanel();
        setLayout(new BorderLayout());
        add(upperPanel, NORTH);
        add(centerPanel, CENTER);
        add(bottomPanel, SOUTH);
    }

    public JPanel buildUpperDatabasePanel() {

        JPanel upperP = new JPanel();
        upperP.setLayout(new FlowLayout());
        ImageIcon backGround = new ImageIcon(IMAGE_DIRECTORY + "newTheme.jpg");
        messageBoard = new JButton("Adding your favorite Movie/TVShow by click on the poster", backGround);

        messageBoard.setForeground(Color.WHITE);
        messageBoard.setFont(new Font("Times New Roman", Font.BOLD, 30));

        messageBoard.setVerticalTextPosition(JButton.CENTER);
        messageBoard.setHorizontalTextPosition(JButton.CENTER);
        messageBoard.setBorderPainted(false);

        upperP.add(messageBoard);
        return upperP;
    }


    public JPanel buildCenterDatabasePanel(int movieSize, int tvshowSize, Uuup up) {
        JPanel panel = new JPanel();
        int posterSize = movieSize + tvshowSize;
        panel.setLayout(new GridLayout(2, posterSize/2));

        posters = new JButton[posterSize];
        for(int i = 1; i <= movieSize; i++) {
            this.media = up.getMovieDataBase().getAMedia(i);
            posters[i] = media.getPoster();
            posters[i].addActionListener(selectedListener);
            panel.add(posters[i]);
        }
        for(int i = 1; i <= tvshowSize; i++) {
            this.media = up.getTvShowDataBase().getAMedia(i);
            posters[i] = media.getPoster();
            posters[i].addActionListener(selectedListener);
            panel.add(posters[i]);
        }
        return panel;
    }

    public JPanel buildBottomPanel() {
        JPanel bottomP = new JPanel();
        JButton done = new JButton("Done");
        done.setBackground(Color.BLUE);

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogOutPage logOutPage = new LogOutPage(cl, panelContainer, up);
                panelContainer.add(logOutPage, "4");
                cl.show(panelContainer, "4");
            }
        });
        bottomP.add(done);
        return bottomP;
    }

    private class SelectedListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            int size = up.getUserList().size();
            Person user = up.getLatestUser();
            MediaList movieList = user.getMovieList();
            MediaList tvShowList = user.getTVShowList();
            MediaPoster mediaPoster = (MediaPoster)event.getSource();
            String posterName = mediaPoster.getPosterName();
            if(!movieList.containMedia(posterName) && !tvShowList.containMedia(posterName)) {
                messageBoard.setText("Success add " + posterName);
                user.addToWishList(posterName, up.getMovieDataBase(), up.getTvShowDataBase());
            }
            else if(movieList.containMedia(posterName) || tvShowList.containMedia(posterName)) {
                String errorMessage = posterName + "is already in your list.";
                messageBoard.setText(errorMessage);
            }

        }
    }

}
