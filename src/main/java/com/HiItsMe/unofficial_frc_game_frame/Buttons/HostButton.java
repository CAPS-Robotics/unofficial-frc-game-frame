package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.LAN.Connection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 7/21/2017.
 * Starts LAN server
 */
public class HostButton extends Button {
    BufferedImage img;
    public HostButton(int X, int Y) {
        x = X;
        y = Y;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/HostButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        Connection.host();
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
