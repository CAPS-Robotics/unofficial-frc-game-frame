package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.Button;
import com.HiItsMe.unofficial_frc_game_frame.Buttons.*;
import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.LAN.Connection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Created by William Herron on 11/12/2017.
 * Allows the user to host or join a LAN game
 */
public class Connect extends FRCScreen {
	BufferedImage img;
	BufferedImage scan;
	boolean scanning = false;
	public void init() {
		buttons = new Button[3];
		buttons[0] = new HostButton(FrameMain.gui.screenRatio[0]/4, 9*FrameMain.gui.screenRatio[1]/10);
		buttons[1] = new ScreenswapButton(FrameMain.gui.screenRatio[0]/2, 9*FrameMain.gui.screenRatio[1]/10, "Back", "PlayerSelect");
		buttons[2] = new RefreshButton(3*FrameMain.gui.screenRatio[0]/4, 9*FrameMain.gui.screenRatio[1]/10);
		try {
			img = ImageIO.read(new File("./src/main/resources/Images/CustomizeScreen.png"));
			scan = ImageIO.read(new File("./src/main/resources/Images/Scanning.png"));
		} catch (Exception e) { e.printStackTrace(); }
		FrameMain.screen = "Connect";
		scan();
	}
	public void draw() {
		FrameMain.gui.g2d.drawImage(img, 0, 0, null);
		FrameMain.gui.drawCenteredText(new Font("Serif", Font.PLAIN, 72), "Connect", FrameMain.gui.screenRatio[0]/2, FrameMain.gui.screenRatio[1]/6);
		if(scanning) { FrameMain.gui.drawCenteredImage(scan, FrameMain.gui.screenRatio[0]/2, FrameMain.gui.screenRatio[1]/2); }
		try {
			//Draw buttons
			for (Button button : buttons) {
				button.draw();
			}
		} catch(Exception e) { e.printStackTrace(); }
	}
	public void scan() {
		scanning = true;
		List<String[]> servers = Connection.scanForServers();
		Button[] tbuttons = new Button[3+servers.size()];
		System.arraycopy(buttons, 0, tbuttons, 0, 3);
		for(int i = 0; i < servers.size(); i++) {
			tbuttons[i+3] = new ServerButton(FrameMain.gui.screenRatio[0]/2, (i+1)*FrameMain.gui.screenRatio[1]/(servers.size()+3), servers.get(i));
		}
		buttons = tbuttons;
		scanning = false;
	}
}
