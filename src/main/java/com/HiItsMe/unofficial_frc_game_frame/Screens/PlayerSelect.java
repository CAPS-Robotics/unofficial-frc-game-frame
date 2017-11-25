package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.Button;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.PlayButton;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.ScreenswapButton;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.LAN.Connection;
import com.HiItsMe.unofficial_frc_game_frame.LAN.SendData.PlayerSelection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

/**
 * Created by William Herron on 7/7/2017.
 * The screen where players choose their controls
 */
public class PlayerSelect extends FRCScreen {
    BufferedImage img;
    public int playerNum;
    //Define all three control schemes
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
    String[] machines = new String[playerNum];
    public int controlsSelected = 0;
    @Override
    public void init() {
        controls = new int[playerNum];
	    machines = new String[playerNum];
        for(int i = 0; i < playerNum; i++) {
            controls[i] = -1;
            machines[i] = "";
        }
        if(playerNum == 1) {
	        buttons = new Button[1];
	        buttons[0] = new PlayButton(2*FrameMain.gui.screenRatio[0]/3, 7*(FrameMain.gui.screenRatio[1]/8));
        } else {
	        buttons = new Button[2];
	        buttons[0] = new PlayButton(2*FrameMain.gui.screenRatio[0]/3, 7*(FrameMain.gui.screenRatio[1]/8));
	        buttons[1] = new ScreenswapButton(FrameMain.gui.screenRatio[0]/3, 7*(FrameMain.gui.screenRatio[1]/8), "Connect", "Connect");
        }
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/PlayerSelectScreen.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void draw() {
        //Draw the correct controls if the player has selected, otherwise draw 9 dashes
        FrameMain.gui.g2d.drawImage(img, 0, 0, null);
        for(int i = 1; i <= playerNum; i++) {
            FrameMain.gui.drawCenteredText(new Font("Serif", BOLD, 48), "Player " + i, i*(FrameMain.gui.screenRatio[0]/(playerNum+1)), FrameMain.gui.screenRatio[1]/3);
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
	        if(Connection.connected) {
		        FrameMain.gui.drawCenteredText(new Font("SansSerif", PLAIN, 36), machines[i-1], i*(FrameMain.gui.screenRatio[0]/(playerNum+1)), 5*FrameMain.gui.screenRatio[1]/7);
	        }
        }
        for(Button button : buttons) {
           button.draw();
        }
    }
    @Override
    public void keyDown(int key) {
	    try {
		    if(key == 32 && controlsSelected > 0 && (!Connection.connected || machines[controlsSelected-1].equals((InetAddress.getLocalHost()+"").split("/")[0]))) {
		        //Delete the previous selected control and return to reselect it
		        controlsSelected--;
		        controls[controlsSelected] = -1;
		        machines[controlsSelected] = "";
		    }
	    } catch(UnknownHostException e) { e.printStackTrace(); }
	    if(controlsSelected < playerNum) {
            //Check if key is part of a control scheme, if so set that control scheme and advance to the next player
            for(int i = 0; i < controlCodes.length; i++) {
                for(int j = 0; j < 9; j++) {
                    if(key == controlCodes[i][j]) {
                        boolean notUsed = true;
                        for(int k = 0; k < controls.length; k++) {
	                        try {
		                        if(controls[k] == i && (!Connection.connected || machines[k].equals((InetAddress.getLocalHost()+"").split("/")[0]))) {
	                                notUsed = false;
	                            }
	                        } catch(UnknownHostException e) { e.printStackTrace(); }
                        }
                        if(notUsed) {
                            controls[controlsSelected] = i;
                            if(Connection.connected) {
                            	try {
                            	    machines[controlsSelected] = (InetAddress.getLocalHost()+"").split("/")[0];
		                            Connection.sendData(new PlayerSelection(machines[controlsSelected], i));
	                            } catch(UnknownHostException e) { e.printStackTrace(); }
                            }
	                        controlsSelected++;
                        }
                    }
                }
            }
        }
    }
    //Receives selection from machine on the network
    @Override
	public void incomingData(Object data) {
	    PlayerSelection selection = (PlayerSelection)data;
	    try {
		    if(!selection.name.equals((InetAddress.getLocalHost()+"").split("/")[0])) {
			    controls[controlsSelected] = selection.controlScheme;
			    machines[controlsSelected] = selection.name;
			    controlsSelected++;
		    }
	    } catch(UnknownHostException e) { e.printStackTrace(); }
    }
}

