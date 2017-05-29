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
 */
public class Title extends FRCScreen {
    BufferedImage img;
    Button[] buttons = {new PlayButton((1256/2)-(253/2), (4*(570/7))-(99/2)), new CustomizeButton((1256/2)-(253/2), (4*(570/5))-(99/2))};
    @Override
    public void init() {
        img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/TitleScreen.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void draw() {
        FrameMain.gui.g2d.drawImage(img, 0, 0, null);
        for(Button button : buttons) {
            button.draw();
        }
    }
}
