package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.*;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.Button;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.*;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * The screen on which you customize your robots
 */
public class Customize extends FRCScreen {
    public int assets;
    BufferedImage img;
    BufferedImage[] drive = new BufferedImage[4];
    BufferedImage robotBase;
    BufferedImage[] speed = new BufferedImage[6];
    BufferedImage[] shooter = new BufferedImage[3];
    UpgradeSelector[] upgs = new UpgradeSelector[4];
    final int maxAssets = 5;
    Font titleFont = new Font("Serif", Font.PLAIN, 72);
    Font lineMarkerFont = new Font("Monospaced", Font.PLAIN, 16);
    Font assetsFont = new Font("Serif", Font.BOLD, 24);
    Font detailFont = new Font("Monospaced", Font.PLAIN, 7);
    BufferedImage robot;
    Graphics2D robotGraphics;
    @Override
    public void init() {
        assets = maxAssets;
        //Initialize buttons
        buttons = new Button[2];
        buttons[0] = new SaveButton(7*(FrameMain.gui.screenRatio[0]/8), 4*(FrameMain.gui.screenRatio[1]/7));
        buttons[1] = new CancelButton(7*(FrameMain.gui.screenRatio[0]/8), 4*(FrameMain.gui.screenRatio[1]/5));
        upgs[0] = new UpgradeSelector(5, 1*(FrameMain.gui.screenRatio[0]/8), 6*(FrameMain.gui.screenRatio[1]/16), 0);
        upgs[1] = new UpgradeSelector(2, 1*(FrameMain.gui.screenRatio[0]/8), 7*(FrameMain.gui.screenRatio[1]/16), 0);
        upgs[2] = new UpgradeSelector(3, 1*(FrameMain.gui.screenRatio[0]/8), 8*(FrameMain.gui.screenRatio[1]/16), 1);
        upgs[3] = new UpgradeSelector(1, 1*(FrameMain.gui.screenRatio[0]/8), 9*(FrameMain.gui.screenRatio[1]/16), 0);
        //Initialize images
        for(int i = 1; i < 4; i++) {
            try {
                drive[i] = ImageIO.read(new File("./src/main/resources/Images/Drive" + i + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 6; i++) {
            try {
                speed[i] = ImageIO.read(new File("./src/main/resources/Images/Speed" + i + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < 3; i++) {
            try {
                shooter[i] = ImageIO.read(new File("./src/main/resources/Images/Shooter" + i + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            robotBase = ImageIO.read(new File("./src/main/resources/Images/RobotBase.png"));
        } catch (Exception e) { e.printStackTrace(); }
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/CustomizeScreen.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void draw() {
        //Draw title, background, and UpgradeSelector labels
        FrameMain.gui.g2d.drawImage(img, 0, 0, null);
        FrameMain.gui.g2d.setFont(titleFont);
        FrameMain.gui.g2d.drawString("Customize Robot", FrameMain.gui.screenRatio[0] / 8, 72);
        FrameMain.gui.g2d.setFont(lineMarkerFont);
        FrameMain.gui.g2d.drawString("SPEED", FrameMain.gui.screenRatio[0] / 32, (6 * (FrameMain.gui.screenRatio[1] / 16)) + 6);
        FrameMain.gui.g2d.drawString("SHOOTER", FrameMain.gui.screenRatio[0] / 32, (7 * (FrameMain.gui.screenRatio[1] / 16)) + 6);
        FrameMain.gui.g2d.drawString("DRIVETRAIN", FrameMain.gui.screenRatio[0] / 32, (8 * (FrameMain.gui.screenRatio[1] / 16)) + 6);
        FrameMain.gui.g2d.drawString("AUTONOMOUS", FrameMain.gui.screenRatio[0] / 32, (9 * (FrameMain.gui.screenRatio[1] / 16)) + 6);
        FrameMain.gui.g2d.setFont(detailFont);
        FrameMain.gui.g2d.drawString("TANK", (FrameMain.gui.screenRatio[0]/8)-8, (8 * (FrameMain.gui.screenRatio[1] / 16))+15);
        FrameMain.gui.g2d.drawString("ARCADE", (FrameMain.gui.screenRatio[0]/8)-12+(25), (8 * (FrameMain.gui.screenRatio[1] / 16))+15);
        FrameMain.gui.g2d.drawString("SWERVE", (FrameMain.gui.screenRatio[0]/8)-12+(25*2), (8 * (FrameMain.gui.screenRatio[1] / 16))+15);
        FrameMain.gui.g2d.setFont(assetsFont);
        FrameMain.gui.g2d.drawString("Assets: " + (maxAssets - assets) + "/" + maxAssets, 2 * (FrameMain.gui.screenRatio[0] / 3), 72);
        try {
            //Draw robot
            robot = FrameMain.gui.gc.createCompatibleImage(50, 50);
            robotGraphics = robot.createGraphics();
            robotGraphics.drawImage(robotBase, 0, 0, null);
            robotGraphics.drawImage(speed[upgs[0].tier], 0, 0, null);
            robotGraphics.drawImage(drive[upgs[2].tier], 0, 0, null);
            robotGraphics.drawImage(shooter[upgs[1].tier], 0, 0, null);
            FrameMain.gui.g2d.drawImage(robot, 40, 20, null);
            SaveButton saveButton = (SaveButton) buttons[0];
            saveButton.saveImage = robot;
            for(int i = 0; i < 4; i++) {
                saveButton.attributeValues[i] = upgs[i].tier;
            }
            Thread.yield();
        } finally {
            if (robotGraphics != null) {
                robotGraphics.dispose();
            }
        }
        try {
            //Draw buttons and UpgradeSelectors
            for (Button button : buttons) {
                button.draw();
            }
            for(UpgradeSelector upg : upgs) {
                upg.draw();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickButtons(int cx, int cy) {
        for(Button button : buttons) {
            button.checkClick(cx, cy);
        }
        for(UpgradeSelector upg : upgs) {
            upg.checkClick(cx, cy);
        }
    }
}
