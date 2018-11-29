package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class LogOutPage extends JPanel {
    private JPanel upperPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JButton[] posters;
    private CardLayout cl;
    private JPanel panelContainer;
    protected Uuup up;
    private final String IMAGE_DIRECTORY = "/..//Users/michauxsun/CPSC210/projectw1_team423/Media Poster/";
    private JLabel messageBoard;
    private SelectedListener selectedListener;
    private JTextArea showList;


    public LogOutPage(CardLayout cl, JPanel panelContainer, Uuup up) {
        this.up = up;
        this.cl = cl;
        this.panelContainer = panelContainer;
        this.selectedListener = new SelectedListener();
        this.messageBoard = new JLabel("Delete media by clicking on the poster");
        upperPanel = buildUpperDatabasePanel();
        centerPanel = buildCenterPanel(up);
        bottomPanel = buildBottomPanel();
        setLayout(new BorderLayout());
        add(upperPanel, NORTH);
        add(centerPanel, CENTER);
        add(bottomPanel, SOUTH);
    }

    private JPanel buildBottomPanel() {
        JPanel buttomP = new JPanel();
        buttomP.setLayout(new BorderLayout());
        JButton signOut = new JButton("Sign Out");
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "1");
            }
        });
        JButton keepAdding = new JButton("Keep Adding");
        keepAdding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "3");
            }
        });
        buttomP.add(signOut, BorderLayout.CENTER);
        buttomP.add(keepAdding, BorderLayout.SOUTH);
        return buttomP;
    }

    private JPanel buildCenterPanel(Uuup up) {
        JPanel mainP = new JPanel();
        mainP.setLayout(new BorderLayout());
        JPanel centerP = new JPanel();
        this.showList = new JTextArea();
        Person user = up.getLatestUser();
        int size = user.getTVShowList().getSize() + user.getMovieList().getSize();
        posters = new JButton[size];
        if(user.getMovieList().getSize() == 0) {
            int i = 1;
            if(user.getTVShowList().getSize() != 0) {
                for(Media media: user.getTVShowList().getMediaList().values()) {
                    posters[i] = media.getPoster();
                    posters[i].addActionListener(selectedListener);
                    centerP.add(posters[i]);
                    i++;
                }
            }
        }
        else if(user.getMovieList().getSize() != 0) {
            if(user.getTVShowList().getSize() != 0) {
                int i = 1;
                for(Media media: user.getMovieList().getMediaList().values()) {
                    posters[i] = media.getPoster();
                    posters[i].addActionListener(selectedListener);
                    centerP.add(posters[i]);
                    i++;
                }
                int j = 1;
                for(Media media: user.getTVShowList().getMediaList().values()) {
                    posters[j] = media.getPoster();
                    posters[j].addActionListener(selectedListener);
                    centerP.add(posters[j]);
                    j++;
                }
            }
            else {
                int i = 1;
                for(Media media: user.getMovieList().getMediaList().values()) {
                    posters[i] = media.getPoster();
                    posters[i].addActionListener(selectedListener);
                    centerP.add(posters[i]);
                    i++;
                }
            }
        }

        showList.append(up.printOutUserWishLists(user));
        mainP.add(showList, NORTH);
        mainP.add(centerP, CENTER);
        mainP.add(messageBoard, SOUTH);
        return mainP;
    }

    private JPanel buildUpperDatabasePanel() {

        JPanel upperP = new JPanel();
        upperP.setLayout(new FlowLayout());
        ImageIcon backGround = new ImageIcon(IMAGE_DIRECTORY + "newTheme.jpg");
        JButton messageLabel = new JButton("Log out or keep adding? ", backGround);

        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont( new Font("Times New Roman", Font.BOLD, 50));

        messageLabel.setVerticalTextPosition(JButton.CENTER);
        messageLabel.setHorizontalTextPosition(JButton.CENTER);
        messageLabel.setBorderPainted(false);

        upperP.add(messageLabel);
        return upperP;
    }

    private class SelectedListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Person user = up.getLatestUser();
            MediaList movieList = user.getMovieList();
            MediaPoster mediaPoster = (MediaPoster)event.getSource();
            String posterName = mediaPoster.getPosterName();
            if(movieList.containMedia("<<"+posterName+">>")) {
                user.getMovieList().deleteMediaByName(posterName);
                messageBoard.setText("Success delete " + posterName);
                showList.setText(null);
                showList.append(up.printOutUserWishLists(user));

            }
            else {
                user.getTVShowList().deleteMediaByName(posterName);
                messageBoard.setText("Success delete " + posterName);
                showList.setText(null);
                showList.append(up.printOutUserWishLists(user));
            }

        }
    }
}
