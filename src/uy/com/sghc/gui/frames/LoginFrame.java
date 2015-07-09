package uy.com.sghc.gui.frames;

import uy.com.sghc.config.FileAccesor;
import uy.com.sghc.config.PropController;
import uy.com.sghc.gui.frames.components.RoundBorder;
import uy.com.sghc.gui.frames.components.RoundJPasswordField;
import uy.com.sghc.gui.frames.components.RoundJTextField;
import uy.com.sghc.gui.listeners.LoginListener;

import javax.swing.*;
import java.awt.*;

public class LoginFrame  extends JFrame {

    private RoundJTextField userText;
    private RoundJPasswordField passwordText;
    private JButton entrarButton;

    public LoginFrame() {
        super(PropController.getPropInterfaz(PropController.INT_LOGIN_TITULO));
        this.setSize(430, 170);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel loginPanel = new JPanel();
        this.add(loginPanel);
        this.initComponentes(loginPanel);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
//        Image icon = new ImageIcon(FileAccesor.getURL(PropController.getPropInterfaz(PropController.INT_LOGIN_LOGO))).getImage();
//        setIconImage(icon);
    }

    private void initComponentes(JPanel panel) {
        panel.setLayout(null);

//        ImageIcon image = new ImageIcon(FileAccesor.getURL(PropController.getPropInterfaz(PropController.INT_LOGIN_LOGO)));
        JLabel label = new JLabel("LABEL");
        label.setBounds(10, 10, 140, 125);
        panel.add(label);

        JLabel userLabel = new JLabel(PropController.getPropInterfaz(PropController.INT_LOGIN_USER));
        userLabel.setBounds(160, 20, 80, 25);
        panel.add(userLabel);

        userText = new RoundJTextField(20);
        userText.setBounds(250, 20, 160, 25);
        userText.setBorder(BorderFactory.createCompoundBorder(userText.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(userText);

        JLabel passwordLabel = new JLabel(PropController.getPropInterfaz(PropController.INT_LOGIN_PASS));
        passwordLabel.setBounds(160, 60, 100, 25);
        panel.add(passwordLabel);

        passwordText = new RoundJPasswordField(20);
        passwordText.setBounds(250, 60, 160, 25);
        passwordText.setBorder(BorderFactory.createCompoundBorder(passwordText.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(passwordText);

        entrarButton = new JButton(PropController.getPropInterfaz(PropController.INT_LOGIN_EBOTON));
        entrarButton.setBounds(310, 100, 100, 25);
        panel.add(entrarButton);

        entrarButton.setBorder(new RoundBorder(10));//10 is the radius
        entrarButton.setForeground(Color.BLACK);

        LoginListener loginListener= new LoginListener(this);
        entrarButton.addActionListener(loginListener);
        passwordText.addActionListener(loginListener);
    }

    public JTextField getUserText() {
        return userText;
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }

    public JButton getEntrarButton() {
        return entrarButton;
    }
}
