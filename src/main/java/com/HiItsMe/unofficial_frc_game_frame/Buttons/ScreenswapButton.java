package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 7/22/2017.
 * Switches to a new screen
 */
public class ScreenswapButton extends Button {
    BufferedImage img;
    String screen;
    public ScreenswapButton(int X, int Y, String imgString, String scr) {
        x = X;
        y = Y;
        screen = scr;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/"+imgString+"Button.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        FrameMain.setScreen(screen);
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
