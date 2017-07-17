package com.HiItsMe.unofficial_frc_game_frame.EventListeners;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import java.awt.event.*;

/**
 * Created by William Herron on 5/29/2017.
 * Send clicks to screens
 */
public class FRCMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        double clickx = e.getX();
        double clicky = e.getY();
        clickx -= (FrameMain.gui.getWidth()-(FrameMain.gui.screenRatio[0]*FrameMain.gui.scaleSize))/2;
        clicky -= (FrameMain.gui.getHeight()-(FrameMain.gui.screenRatio[1]*FrameMain.gui.scaleSize))/2;
        clickx /= FrameMain.gui.scaleSize;
        clicky /= FrameMain.gui.scaleSize;
        FrameMain.screens.get(FrameMain.screen).clickButtons((int)clickx, (int)clicky);
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
