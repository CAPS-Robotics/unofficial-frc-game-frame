package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * Leave Customize screen without saving
 */
public class CancelButton extends Button {
    BufferedImage img;
    public CancelButton(int X, int Y) {
        x = X;
        y = Y;
        img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/CancelButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        FrameMain.setScreen("Title");
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
