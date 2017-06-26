package uy.com.sghc.main;

import javax.swing.*;

import uy.com.sghc.gui.frames.LoginFrame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame log = new LoginFrame();
                log.setVisible(true);
            }
        });
    }
}
