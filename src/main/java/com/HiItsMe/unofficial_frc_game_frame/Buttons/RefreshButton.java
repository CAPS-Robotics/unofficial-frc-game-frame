package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.Screens.Connect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 7/21/2017.
 * Starts LAN server
 */
public class RefreshButton extends Button {
    BufferedImage img;
    public RefreshButton(int X, int Y) {
        x = X;
        y = Y;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/ConnectButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void trigger() {
        ((Connect)FrameMain.screens.get(FrameMain.screen)).scan();
    }
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
