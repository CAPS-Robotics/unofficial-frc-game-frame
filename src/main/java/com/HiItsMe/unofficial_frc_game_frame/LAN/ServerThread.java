package com.HiItsMe.unofficial_frc_game_frame.LAN;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by William Herron on 11/11/2017.
 * Threads for the server to keep track of each client
 */
public class ServerThread extends Thread {
	private Socket socket;
	public boolean run = true;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		try(InputStream in = socket.getInputStream()) {
			while(run) {
				int av = in.available();
				if(av > 0) {
					byte[] out = new byte[av];
					in.read(out);
					Connection.server.send(out);
				}
			}
		} catch(IOException e) { e.printStackTrace(); }
	}
	public void send(byte[] out) {
		try {
			socket.getOutputStream().write(out);
		} catch(IOException e) { e.printStackTrace(); }
	}
}
