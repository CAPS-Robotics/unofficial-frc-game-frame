package com.HiItsMe.unofficial_frc_game_frame.LAN.SendData;

import java.io.Serializable;

/**
 * Created by William Herron on 11/23/2017.
 * Player selection to send to all machines connected to the network
 */
public class PlayerSelection implements Serializable {
	public String name;
	public int controlScheme;
	public PlayerSelection(String name, int controlScheme) {
		this.name = name; this.controlScheme = controlScheme;
	}
}
