package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.*;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * The Screen where you pick the robots you'll play the next game with
 */
public class Start extends FRCScreen {
    public int gamemode = 0;
    int pgamemode = 0;
    public int[] robots = {0, 0};
    BufferedImage img;
    RobotSelector[] robotSelectors = new RobotSelector[2];
    @Override
    public void init() {
        buttons = new Button[4];
        buttons[0] = new AIButton(FrameMain.gui.screenRatio[0]/5, 5*(FrameMain.gui.screenRatio[1]/6));
        buttons[1] = new PlayerCountButton(2*(FrameMain.gui.screenRatio[0]/5), 5*(FrameMain.gui.screenRatio[1]/6));
        buttons[2] = new CancelButton(3*(FrameMain.gui.screenRatio[0]/5), 5*(FrameMain.gui.screenRatio[1]/6));
        buttons[3] = new StartButton(4*(FrameMain.gui.screenRatio[0]/5), 5*(FrameMain.gui.screenRatio[1]/6));
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/StartScreen.png"));
        } catch (Exception e) { e.printStackTrace(); }
        switchGameMode();
    }
    @Override
    public void draw() {
        FrameMain.gui.g2d.drawImage(img, 0, 0, null);
        for(Button button : buttons) {
            try {
                button.draw();
            } catch(Exception e) { e.printStackTrace(); }
        }
        for(RobotSelector robotSelector : robotSelectors) {
            try {
                robotSelector.draw();
            } catch(Exception e) { e.printStackTrace(); }
        }
        //check buttons for gamemode
        AIButton aiButton = (AIButton)buttons[0];
        PlayerCountButton playerCountButton = (PlayerCountButton)buttons[1];
        pgamemode = gamemode;
        gamemode = 0;
        if(!aiButton.AI) {
            gamemode += 1;
        }
        if(playerCountButton.three) {
            gamemode += 2;
        }
        if(pgamemode != gamemode) { switchGameMode(); }
    }
    @Override
    public void clickButtons(int cx, int cy) {
        for(Button button : buttons) {
            button.checkClick(cx, cy);
        }
        for(RobotSelector robotSelector : robotSelectors) {
            robotSelector.checkClick(cx, cy);
        }
    }
    public void switchGameMode() {
        //Set appropriate robotSelectors based on game mode
        switch(gamemode) {
            case 0:
                robotSelectors = new RobotSelector[2];
                robots = new int[2];
                for(int i = 0; i < robots.length; i++) {
                    robots[i] = 0;
                }
                robotSelectors[0] = new RobotSelector(FrameMain.gui.screenRatio[0]/4, FrameMain.gui.screenRatio[1]/3, 0, 1);
                robotSelectors[1] = new RobotSelector(3*(FrameMain.gui.screenRatio[0]/4), FrameMain.gui.screenRatio[1]/3, 1, 0);
                break;
            case 1:
                robotSelectors = new RobotSelector[2];
                robots = new int[2];
                for(int i = 0; i < robots.length; i++) {
                    robots[i] = 0;
                }
                robotSelectors[0] = new RobotSelector(FrameMain.gui.screenRatio[0]/4, FrameMain.gui.screenRatio[1]/3, 0, 1);
                robotSelectors[1] = new RobotSelector(3*(FrameMain.gui.screenRatio[0]/4), FrameMain.gui.screenRatio[1]/3, 1, 2);
                break;
            case 2:
                robotSelectors = new RobotSelector[6];
                robots = new int[6];
                for(int i = 0; i < robots.length; i++) {
                    robots[i] = 0;
                }
                robotSelectors[0] = new RobotSelector(FrameMain.gui.screenRatio[0]/6, FrameMain.gui.screenRatio[1]/4, 0, 1);
                robotSelectors[1] = new RobotSelector(2*(FrameMain.gui.screenRatio[0]/6), FrameMain.gui.screenRatio[1]/4, 1, 1);
                robotSelectors[2] = new RobotSelector(FrameMain.gui.screenRatio[0]/4, 2*(FrameMain.gui.screenRatio[1]/4), 2, 1);
                robotSelectors[3] = new RobotSelector(4*(FrameMain.gui.screenRatio[0]/6), FrameMain.gui.screenRatio[1]/4, 3, 0);
                robotSelectors[4] = new RobotSelector(5*(FrameMain.gui.screenRatio[0]/6), FrameMain.gui.screenRatio[1]/4, 4, 0);
                robotSelectors[5] = new RobotSelector(3*(FrameMain.gui.screenRatio[0]/4), 2*(FrameMain.gui.screenRatio[1]/4), 5, 0);
                break;
            case 3:
                robots = new int[6];
                for(int i = 0; i < robots.length; i++) {
                    robots[i] = 0;
                }
                robotSelectors = new RobotSelector[6];
                robotSelectors[0] = new RobotSelector(FrameMain.gui.screenRatio[0]/6, FrameMain.gui.screenRatio[1]/4, 0, 1);
                robotSelectors[1] = new RobotSelector(2*(FrameMain.gui.screenRatio[0]/6), FrameMain.gui.screenRatio[1]/4, 1, 1);
                robotSelectors[2] = new RobotSelector(FrameMain.gui.screenRatio[0]/4, 2*(FrameMain.gui.screenRatio[1]/4), 2, 1);
                robotSelectors[3] = new RobotSelector(4*(FrameMain.gui.screenRatio[0]/6), FrameMain.gui.screenRatio[1]/4, 3, 2);
                robotSelectors[4] = new RobotSelector(5*(FrameMain.gui.screenRatio[0]/6), FrameMain.gui.screenRatio[1]/4, 4, 2);
                robotSelectors[5] = new RobotSelector(3*(FrameMain.gui.screenRatio[0]/4), 2*(FrameMain.gui.screenRatio[1]/4), 5, 2);
                break;
        }
    }
}
