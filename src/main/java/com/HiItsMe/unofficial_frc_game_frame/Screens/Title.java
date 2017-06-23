package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.Button;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.StartButton;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;


/**
 * Created by William Herron on 5/20/2017.
 * The title screen
 */
public class Title extends FRCScreen {
    BufferedImage img;
    @Override
    public void init() {
        //Initialize buttons
        buttons = new Button[2];
        buttons[0] = new PlayButton((FrameMain.gui.screenRatio[0]/2)-(253/2), (4*(FrameMain.gui.screenRatio[1]/7))-(99/2));
        buttons[1] = new CustomizeButton((FrameMain.gui.screenRatio[0]/2)-(253/2), (4*(FrameMain.gui.screenRatio[1]/5))-(99/2));
        //Initialize bg
        img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/TitleScreen.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void draw() {
        //Draw buttons and bg
        FrameMain.gui.g2d.drawImage(img, 0, 0, null);
        try {
            for (Button button : buttons) {
                button.draw();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
