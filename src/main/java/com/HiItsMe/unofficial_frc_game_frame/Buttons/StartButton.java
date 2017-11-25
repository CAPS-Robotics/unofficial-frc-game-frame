package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.Screens.PlayerSelect;
import com.HiItsMe.unofficial_frc_game_frame.Screens.Start;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * Open to control select screen
 */
public class StartButton extends Button {
    BufferedImage img;
    public StartButton(int X, int Y) {
        x = X;
        y = Y;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/StartButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        //Set the number of players for the
        PlayerSelect playerSelect = (PlayerSelect)FrameMain.screens.get("PlayerSelect");
        Start startScreen = (Start)FrameMain.screens.get("Start");
        switch(startScreen.gamemode) {
            case 0:
                playerSelect.playerNum = 1;
                break;
            case 1:
                playerSelect.playerNum = 2;
                break;
            case 2:
                playerSelect.playerNum = 3;
                break;
            case 3:
                playerSelect.playerNum = 6;
        }
        if(playerSelect.playerNum == 6) {
            FrameMain.setScreen("Connect");
        } else {
            FrameMain.setScreen("PlayerSelect");
        }
    }
    @Override
    public void draw() {
        FrameMain.gui.drawCenteredImage(img, x, y);
    }
}
