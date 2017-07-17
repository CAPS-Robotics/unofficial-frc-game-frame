package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * Open to customize screen
 */
public class CustomizeButton extends Button {
    BufferedImage img;
    public CustomizeButton(int X, int Y) {
        x = X;
        y = Y;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/CustomizeButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        FrameMain.setScreen("Customize");
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
