package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import java.awt.*;

/**
 * Created by William Herron on 5/20/2017.
 */
public class Title extends FRCScreen {
    @Override
    public void init() {

    }
    @Override
    public void draw() {
        FrameMain.gui.g2d.setColor(new Color(255, 0, 0));
        FrameMain.gui.g2d.fillRect(50, 50, 50, 50);
    }
}
