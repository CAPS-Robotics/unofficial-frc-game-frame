package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * Open to robot select screen
 */
public class PlayButton extends Button {
    BufferedImage img;
    public PlayButton(int X, int Y) {
        x = X;
        y = Y;
        img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/PlayButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        FrameMain.setScreen("Start");
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
