package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.LAN.Connection;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by William Herron on 11/13/2017.
 * Displays and connects to servers found by the scan
 */
public class ServerButton extends Button {
	String[] server = new String[2];
	public ServerButton(int X, int Y, String[] server) {
		this.server = server;
		x = X; y = Y;
		
	}
	public void trigger() {
		try {
			if(!String.join("/", server).equals(""+InetAddress.getLocalHost())) {
				Connection.join(server[1]);
			}
		} catch(UnknownHostException e) { e.printStackTrace(); }
	}
	public void draw() {
		FrameMain.gui.g2d.setPaint(new Color(0));
		try {
			if(String.join("/", server).equals(""+InetAddress.getLocalHost())) {
				FrameMain.gui.g2d.setPaint(new Color(0xff0000));
			}
		} catch(Exception e) { /*I literally couldn't care less*/ }
		FrameMain.gui.g2d.drawRect(x-(bwidth/2), y-(bheight/2), bwidth, bheight);
		FrameMain.gui.g2d.setPaint(new Color(0));
		FrameMain.gui.drawCenteredText(new Font("Monospace", Font.BOLD, 20), server[0], x, y);
	}
}
