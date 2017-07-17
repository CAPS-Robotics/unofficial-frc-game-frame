package com.HiItsMe.unofficial_frc_game_frame.EventListeners;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import java.awt.event.*;

/**
 * Created by William Herron on 5/29/2017.
 * Kill window if esc pressed, pass other keys to active screen
 */
public class FRCKeyListener implements KeyListener {
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 27) {
            FrameMain.gui.dispose();
            System.exit(0);
        } else {
            FrameMain.screens.get(FrameMain.screen).keyDown(e.getKeyCode());
        }
    }
    public void keyReleased(KeyEvent e) {
        FrameMain.screens.get(FrameMain.screen).keyUp(e.getKeyCode());
    }
    public void keyTyped(KeyEvent e) {}
}
