package com.HiItsMe.unofficial_frc_game_frame;

import java.awt.event.*;

/**
 * Created by William Herron on 5/29/2017.
 */
public class FRCMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        int clickx = e.getX();
        int clicky = e.getY();
        clickx -= (int)((FrameMain.gui.getWidth()-(FrameMain.gui.screenRatio[0]*FrameMain.gui.scaleSize))/2);
        clicky -= (int)((FrameMain.gui.getHeight()-(FrameMain.gui.screenRatio[1]*FrameMain.gui.scaleSize))/2);
        clickx *= FrameMain.gui.scaleSize;
        clicky *= FrameMain.gui.scaleSize;
        FrameMain.screens.get(FrameMain.screen).clickButtons(clickx, clicky);
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
