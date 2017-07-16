package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 7/15/2017.
 * Begin game
 */
public class Play2Button extends Button {
    BufferedImage img;
    public Play2Button(int X, int Y) {
        x = X;
        y = Y;
        img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/PlayButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        FrameMain.setScreen("Game");
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
