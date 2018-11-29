package ui;

import model.Person;
import model.Uuup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage extends JPanel{
    private JPanel userInput;
    private JButton confirmButton;
    private JLabel errorLabel;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField genderField;
    private JTextField pwField;
    private JTextField confirmPWField;
    private static Font signFont = new Font("Brush Script MT", Font.BOLD, 30);


    public SignInPage(CardLayout cl, JPanel panelContainer, Uuup up) {
        userInput = new JPanel();
        userInput.setBackground(Color.YELLOW.brighter());
        userInput.setLayout(new GridLayout(5,2));
        userInput.setPreferredSize(new Dimension(730, 500));

        JLabel nameLabel = new JLabel("Please create your account name: ", SwingConstants.CENTER);
        userInput.add(nameLabel);
        nameField = new JTextField(10);
        userInput.add(nameField);
        JLabel emailLabel =new JLabel("Please create your email address:", SwingConstants.CENTER);
        userInput.add(emailLabel);
        emailField = new JTextField(10);
        userInput.add(emailField);
        JTextArea genderArea = new JTextArea("Please choose your gender" + '\n' + "(1 for F; 2 for M; 3 for Trans;" + '\n' + "4 for rather not tell): ");
        genderArea.setBackground(Color.YELLOW.brighter());
        userInput.add(genderArea);
        genderField = new JTextField(10);
        userInput.add(genderField);
        JLabel passwordLabel = new JLabel("Please set your password: ", SwingConstants.CENTER);
        userInput.add(passwordLabel);
        pwField = new JTextField(10);
        userInput.add(pwField);
        JTextArea confirmPWLabel = new JTextArea("Please confirm your password \n (you have to reset if two pw don't match): ");
        confirmPWLabel.setBackground(Color.YELLOW.brighter());
        userInput.add(confirmPWLabel);
        confirmPWField = new JTextField(10);
        userInput.add(confirmPWField);

        JPanel confirmP = new JPanel();
        confirmButton = new JButton("Submit");
        confirmButton.setBackground(Color.MAGENTA);
        confirmButton.setFont(signFont);
        confirmButton.setOpaque(true);
        confirmButton.setBorderPainted(false);
        confirmP.setPreferredSize(new Dimension(730, 100));
        confirmP.setBackground(Color.YELLOW.brighter());
        confirmP.add(confirmButton);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validInput()) {
                    Person user = new Person(nameField.getText(), emailField.getText(), Integer.parseInt(genderField.getText()));
                    up.addUser(user);
                    ChooseFavMedia chooseFavMedia = new ChooseFavMedia(cl, panelContainer, up);

                    panelContainer.add(chooseFavMedia, "3");

                    cl.show(panelContainer, "3");
                }
            }
        });

        setBackground(new Color(160, 216, 254));
        setLayout(new BorderLayout());
        add(userInput, BorderLayout.NORTH);
        add(confirmP, BorderLayout.CENTER);
        add(errorLabel,BorderLayout.SOUTH);
    }

    private boolean validInput() {
        try {
            while(nameField.getText().equals("")) {
                errorLabel.setText("User name cannot be empty.");
                return false;
            }
            int gender = Integer.parseInt(genderField.getText());
            while((!genderField.getText().equals("")) && (gender < 1 ||gender > 4)) {
                errorLabel.setText("Cannot identify your input of gender. Please enter again.");
                genderField.setText("");
                return false;
            }

            String pw = pwField.getText();
            while(!pw.equals(confirmPWField.getText())) {
                errorLabel.setText("Password not match. Please enter again.");
                pwField.setText("");
                confirmPWField.setText("");
                return false;
            }
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}
