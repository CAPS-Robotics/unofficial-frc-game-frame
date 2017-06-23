package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 6/22/2017.
 * Used to toggle blue team AI
 */
public class AIButton extends Button {
    BufferedImage img;
    public boolean AI = true;
    public AIButton(int X, int Y) {
        x = X;
        y = Y;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/AIButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        AI = !AI;
    }
    @Override
    public void draw() {
        FrameMain.gui.g2d.drawImage(img, x, y, null);
    }
}
