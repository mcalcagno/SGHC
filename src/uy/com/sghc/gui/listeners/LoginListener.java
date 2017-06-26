package uy.com.sghc.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import uy.com.sghc.config.PropController;
import uy.com.sghc.gui.frames.LoginFrame;
import uy.com.sghc.gui.frames.PrincipalFrame;

public class LoginListener implements ActionListener {
    protected static PrincipalFrame desktop = new PrincipalFrame();
    private LoginFrame login;

    public LoginListener(final LoginFrame login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Se apreta el boton de entrar al sistema
        if (e.getSource()==this.login.getEntrarButton()
                || e.getSource()==this.login.getPasswordText()
                || e.getSource()==this.login.getUserText()) {
            // Usuario vacio
            if (StringUtils.isEmpty(this.login.getUserText().getText())) {
                JOptionPane.showMessageDialog(this.login, PropController.getPropMessage(PropController.MESS_LOGIN_USUARIO_VACIO), "Error",JOptionPane.ERROR_MESSAGE);
                this.login.getUserText().requestFocus();
            }
            // Password vacia
            else if (this.login.getPasswordText().getPassword().length==0) {
                JOptionPane.showMessageDialog(this.login, PropController.getPropMessage(PropController.MESS_LOGIN_PASSWORD_VACIO), "Error",JOptionPane.ERROR_MESSAGE);
                this.login.getPasswordText().requestFocus();
            }
            // Autenticar, se pasa usuario y password
            else {
                if (authenticateUser(this.login.getUserText().getText(), this.login.getPasswordText().getPassword())) {
                    desktop.setVisible(true);
                    this.login.dispose();
                }
                else {
                    this.login.getUserText().setText(StringUtils.EMPTY);
                    this.login.getPasswordText().setText(StringUtils.EMPTY);
                    this.login.getUserText().requestFocus();
                }
            }
        }
    }
    
    /**
     * Por ahora controla que el usuario y password sean "admin"
     * @param user
     * @param password
     */
    private boolean authenticateUser(final String user, final char[] password) {
        return user!=null && StringUtils.equalsIgnoreCase("admin", user) && StringUtils.equalsIgnoreCase("admin", String.valueOf(password));
    }
}
