package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.Button;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.ScreenswapButton;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


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
        buttons[0] = new ScreenswapButton(FrameMain.gui.screenRatio[0]/2, 4*(FrameMain.gui.screenRatio[1]/7), "Play", "Start");
        buttons[1] = new ScreenswapButton(FrameMain.gui.screenRatio[0]/2, 4*(FrameMain.gui.screenRatio[1]/5), "Customize", "Customize");
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
