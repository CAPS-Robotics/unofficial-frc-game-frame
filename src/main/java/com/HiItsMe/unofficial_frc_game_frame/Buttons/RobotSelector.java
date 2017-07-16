package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.Screens.Start;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 6/21/2017.
 * Used to change the robot for each player on the Start screen
 */
public class RobotSelector {
    BufferedImage[] robotImgs;
    BufferedImage[] arrows = new BufferedImage[3];
    File[] robotFiles = new File("./src/main/resources/Robots").listFiles();
    private int _x, _y, _selectorNum, _color;
    public int robot = 0;
    int robots = 1;
    public RobotSelector(int x, int y, int selectorNum, int color) {
         _x = x; _y = y; _selectorNum = selectorNum; _color = color;
        //get number of robots
        robots = 0;
        for(File robotFile : robotFiles) {
            if(robotFile.getName().matches("Robot\\d+\\.png")) {
                robots++;
            }
        }
        //init images
        robotImgs = new BufferedImage[robots];
        for(int i = 0; i < robots; i++) {
            try {
                robotImgs[i] = ImageIO.read(new File("./src/main/resources/Robots/Robot" + i + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 3; i++) {
            try {
                arrows[i] = ImageIO.read(new File("./src/main/resources/Images/Arrows" + i + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void draw() {
        FrameMain.gui.drawCenteredImage(arrows[_color], _x, _y);
        FrameMain.gui.drawCenteredImage(robotImgs[robot], _x, _y);
    }
    public void checkClick(int cx, int cy) {
        if(cx > _x - 50 && cx < _x - 25 && cy > _y - 25 && cy < _y + 25) {
            if(robot == 0) {
                robot = robots - 1;
            } else {
                robot--;
            }
            Start scr = (Start)(FrameMain.screens.get(FrameMain.screen));
            scr.robots[_selectorNum] = robot;
        } else if(cx > _x + 25 && cx < _x + 50 && cy > _y - 25 && cy < _y + 25) {
            if(robot == robots - 1) {
                robot = 0;
            } else {
                robot++;
            }
            Start scr = (Start)(FrameMain.screens.get(FrameMain.screen));
            scr.robots[_selectorNum] = robot;
        }
    }
}
