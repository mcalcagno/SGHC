package uy.com.sghc.main;

import javax.swing.*;

import uy.com.sghc.gui.frames.LoginFrame;

public class MainFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                Desktop desktopFrame = new Desktop();
//                desktopFrame.setVisible(true); // display frame*/
                LoginFrame log = new LoginFrame();
                log.setVisible(true);
            }
        });
    }
}
