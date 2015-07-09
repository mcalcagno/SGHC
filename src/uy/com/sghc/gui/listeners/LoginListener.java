package uy.com.sghc.gui.listeners;

import javax.swing.*;

import uy.com.sghc.gui.frames.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by saul on 16/12/14.
 */
public class LoginListener implements ActionListener {
//    private static Logger logger = Logger.getLogger(LoginListener.class);

    //protected static JFrame desktop = new Desktop();
    private LoginFrame login;

    public LoginListener(LoginFrame login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Se apreta el boton de entrar al sistema
        if (e.getSource()==this.login.getEntrarButton()
                || e.getSource()==this.login.getPasswordText()
                || e.getSource()==this.login.getUserText()) {
            // Usuario vacio
            if (this.login.getUserText().getText().equals("")) {
//                JOptionPane.showMessageDialog(this.login, PropController.getPropMessage(PropController.MESS_LOGIN_USUARIO_VACIO), "Error",JOptionPane.ERROR_MESSAGE);
                this.login.getUserText().requestFocus();
            }
            // Password vacia
            else if (this.login.getPasswordText().getPassword().length==0) {
//                JOptionPane.showMessageDialog(this.login, PropController.getPropMessage(PropController.MESS_LOGIN_PASSWORD_VACIO), "Error",JOptionPane.ERROR_MESSAGE);
                this.login.getPasswordText().requestFocus();
            }
            // Autenticar, se pasa usuario y password
            else {
                if (authenticateUser(this.login.getUserText().getText(),
                        this.login.getPasswordText().getPassword())) {
      //              desktop.setVisible(true);
                    this.login.dispose();
                }
                else {
                    this.login.getUserText().setText("");
                    this.login.getPasswordText().setText("");
                    this.login.getUserText().requestFocus();
                }
            }
        }
    }

    // Método que hace la autenticación del usuario llamando al AppManager
    private boolean authenticateUser(String user, char[] password) {
        //try {
            //return AppManager.authenticateUser(user, password);
        //}catch (WSException e){
            /*this.logger.error(PropController.getPropMessage(e.getType()));
            JOptionPane.showMessageDialog(null, PropController.getPropMessage(e.getType()),
                    "Error", JOptionPane.ERROR_MESSAGE);*/
            return true;
    //    }
    }
}
