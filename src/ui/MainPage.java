package ui;

import model.Uuup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

public class MainPage extends JPanel {
    private JPanel greetingArea;
    private JPanel centerPanel;
    private JPanel southPanel;
    private CardLayout cl;
    private JPanel panelContainer;
    private Uuup up;

    private String GREETING_MESSAGE = "Hey! What took you so long??";
    private final String IMAGE_DIRECTORY = "/..//Users/michauxsun/CPSC210/projectw1_team423/Media Poster/";
    private static Font greetingFont = new Font("Times New Roman", Font.BOLD, 56);
    private static Font introFont = new Font("Times New Roman", Font.BOLD, 50);
    private static Font signFont = new Font("Brush Script MT", Font.BOLD, 30);

    public MainPage(CardLayout cl, JPanel panelContainer, Uuup up) {
        setLayout(new BorderLayout());
        this.cl = cl;
        this.panelContainer = panelContainer;
        this.up = up;

        greetingArea = buildNorthTextArea();
        greetingArea.setPreferredSize(new Dimension(730, 100));
        centerPanel = buildCenterPanel();
        centerPanel.setPreferredSize(new Dimension(730, 600));
        southPanel = buildSouthPanel();
        southPanel.setPreferredSize(new Dimension(730, 60));

        add(greetingArea, NORTH);
        add(centerPanel, CENTER);
        add(southPanel, SOUTH);
    }

    private JPanel buildSouthPanel() {
        JPanel southP = new JPanel();
        southP.setBackground(new Color(160, 216, 254));

        JButton signInButton = new JButton("Sign in");
        signInButton.setFont(signFont);
        southP.add(signInButton);

        return southP;
    }

    private JPanel buildCenterPanel() {
        JPanel centerP = new JPanel();
        centerP.setLayout(new BorderLayout());

        JPanel upperP = new JPanel();
        upperP.setBackground(new Color(160, 216, 254));
        JButton signUpButton = new JButton("Sign up");
        signUpButton.setFont(signFont);
        centerP.setBackground(new Color(160, 216, 254));
        signUpButton.setPreferredSize(new Dimension(100, 60));

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInPage signInPage = new SignInPage(cl, panelContainer, up);
                panelContainer.add(signInPage, "2");
                cl.show(panelContainer, "2");
            }
        });

        upperP.add(signUpButton);

        JPanel smallP = new JPanel();
        JButton mediaPoster = new JButton();
        mediaPoster.setOpaque(true);
        mediaPoster.setBorderPainted(false);
        mediaPoster.setIcon(new ImageIcon(IMAGE_DIRECTORY + "Aquaman.jpg"));

        JLabel introText = new JLabel("Coming soon: ", SwingConstants.CENTER);
        introText.setFont(introFont);

        smallP.setLayout(new BorderLayout());
        smallP.add(introText, NORTH);
        smallP.add(mediaPoster, CENTER);

        JLabel sourceLabel = new JLabel("Poster source: https://www.rottentomatoes.com/m/aquaman_2018/", SwingConstants.CENTER);

        centerP.add(upperP, NORTH);
        centerP.add(smallP, CENTER);
        centerP.add(sourceLabel, SOUTH);

        return centerP;
    }

    private JPanel buildNorthTextArea() {
        JPanel greetingP = new JPanel();
        greetingP.setLayout(new FlowLayout());
        ImageIcon backGround = new ImageIcon(IMAGE_DIRECTORY + "newTheme.jpg");
        JButton greetingTA = new JButton(GREETING_MESSAGE, backGround);

        greetingTA.setForeground(Color.WHITE);
        greetingTA.setFont(greetingFont);

        greetingTA.setVerticalTextPosition(JButton.CENTER);
        greetingTA.setHorizontalTextPosition(JButton.CENTER);
        greetingTA.setBorderPainted(false);

        greetingP.add(greetingTA);
        return greetingP;
    }
}