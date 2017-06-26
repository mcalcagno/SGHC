package uy.com.sghc.gui.frames;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import uy.com.sghc.config.FileAccesor;
import uy.com.sghc.config.PropController;
import uy.com.sghc.gui.frames.components.RoundBorder;
import uy.com.sghc.gui.frames.components.RoundJPasswordField;
import uy.com.sghc.gui.frames.components.RoundJTextField;
import uy.com.sghc.gui.listeners.LoginListener;

public class LoginFrame  extends JFrame {

	private static final long serialVersionUID = 1L;
	
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
        
        Image icon = null;
        if (FileAccesor.getURL(PropController.getPropInterfaz(PropController.INT_LOGIN_LOGOICON))!=null) {
        	icon = new ImageIcon(FileAccesor.getURL(PropController.getPropInterfaz(PropController.INT_LOGIN_LOGOICON))).getImage();
        }        
        setIconImage(icon);
    }

    private void initComponentes(final JPanel panel) {
        panel.setLayout(null);
        URL url = FileAccesor.getURL(PropController.getPropInterfaz(PropController.INT_LOGIN_LOGO));
        ImageIcon image = new ImageIcon(); 
        if (url!=null) {
        	image = new ImageIcon(url);	
        }
        
        JLabel label = new JLabel();
        label.setBounds(10, 10, 140, 125);
        label.setIcon(image);
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

        entrarButton = new JButton(PropController.getPropInterfaz(PropController.INT_LOGIN_BOTON));
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
