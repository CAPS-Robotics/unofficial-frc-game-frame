package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 6/22/2017.
 * Used to swap between 3v3 and 1v1 mode
 */
public class PlayerCountButton extends Button {
    BufferedImage[] imgs = new BufferedImage[2];
    public boolean three = false;
    public PlayerCountButton(int X, int Y) {
        x = X;
        y = Y;
        try {
            imgs[0] = ImageIO.read(new File("./src/main/resources/Images/SingleButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
        try {
            imgs[1] = ImageIO.read(new File("./src/main/resources/Images/TripleButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        three = !three;
    }
    @Override
    public void draw() {
        if(three) {
            FrameMain.gui.drawCenteredImage(imgs[0], x, y);
        } else {
            FrameMain.gui.drawCenteredImage(imgs[1], x, y);
        }
    }
}
