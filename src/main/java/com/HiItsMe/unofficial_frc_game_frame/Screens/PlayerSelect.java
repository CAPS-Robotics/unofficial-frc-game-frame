package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.Button;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.Play2Button;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.Font.*;

/**
 * Created by William Herron on 7/7/2017.
 * The screen where players choose their controls
 */
public class PlayerSelect extends FRCScreen {
    BufferedImage img;
    public int playerNum;
    String[][][] allControls = {
        {
            {"q", "w", "e"},
            {"a", "s", "d"},
            {"z", "x", "c"}
        },
        {
            {"u", "i", "o"},
            {"j", "k", "l"},
            {"n", "m", ","}
        },
        {
            {"7", "8", "9"},
            {"4", "5", "6"},
            {"1", "2", "3"}
        }
    };
    int[][] controlCodes = {
        {81, 87, 69, 65, 83, 68, 90, 88, 67},
        {85, 73, 79, 74, 75, 76, 78, 77, 44},
        {103, 104, 105, 100, 101, 102, 97, 98, 99}
    };
    int[] controls = new int[playerNum];
    public int controlsSelected = 0;
    @Override
    public void init() {
        controls = new int[playerNum];
        for(int i = 0; i < playerNum; i++) {
            controls[i] = -1;
        }
        if(playerNum == 6) {
            FrameMain.setScreen("Start");
        } else {
            buttons = new Button[1];
            buttons[0] = new Play2Button(FrameMain.gui.screenRatio[0]/2, 7*(FrameMain.gui.screenRatio[1]/8));
        }
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/CustomizeScreen.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void draw() {
        FrameMain.gui.g2d.drawImage(img, 0, 0, null);
        for(int i = 1; i <= playerNum; i++) {
            FrameMain.gui.drawCenteredText(new Font("Serif", BOLD, 64), "Player " + i, i*(FrameMain.gui.screenRatio[0]/(playerNum+1)), FrameMain.gui.screenRatio[1]/3);
            String[][] drawControls = new String[3][3];
            if(controls[i-1] == -1) {
                for(int j = 0; j < 3; j++) {
                    for(int k = 0; k < 3; k++) {
                        drawControls[j][k] = "-";
                    }
                }
            } else {
                drawControls = allControls[controls[i-1]];
            }
            int[] pos = new int[2];
            pos[1] = (FrameMain.gui.screenRatio[1]/2)-50;
            pos[0] = (i*(FrameMain.gui.screenRatio[0]/(playerNum+1)))-50;
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    FrameMain.gui.drawCenteredText(new Font("Monospaced", PLAIN, 32), drawControls[k][j], pos[0]+(j*50), pos[1]+(k*50));
                }
            }
        }
        buttons[0].draw();
    }
    @Override
    public void keyDown(int key) {
        if(key == 32 && controlsSelected > 0) {
            controlsSelected--;
            controls[controlsSelected] = -1;
        }
        if(controlsSelected < playerNum) {
            for(int i = 0; i < controlCodes.length; i++) {
                for(int j = 0; j < 9; j++) {
                    if(key == controlCodes[i][j]) {
                        boolean notUsed = true;
                        for(int control : controls) {
                            if(control == i) {
                                notUsed = false;
                            }
                        }
                        if(notUsed) {
                            controls[controlsSelected] = i;
                            controlsSelected++;
                        }
                    }
                }
            }
        }
    }
}

