package com.HiItsMe.unofficial_frc_game_frame;

import java.awt.event.*;

/**
 * Created by William Herron on 5/29/2017.
 */
public class FRCKeyListener implements KeyListener {
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 27) {
            FrameMain.gui.dispose();
            System.exit(0);
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
