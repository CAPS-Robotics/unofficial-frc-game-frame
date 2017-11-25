package com.HiItsMe.unofficial_frc_game_frame.LAN;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by William Herron on 11/13/2017.
 * Responds to server searches
 */
public class BroadcastReceiver {
	Thread t = null;
	boolean loop = true;
	public BroadcastReceiver() {
		try {
			MulticastSocket socket = new MulticastSocket(Connection.BROADCAST_PORT);
			InetAddress address = InetAddress.getByName(Connection.BROADCAST_IP_GROUP);
			socket.joinGroup(address);
			t = new Thread(()->{
				try {
					while(loop) {
						byte[] buf = new byte[256];
						DatagramPacket packet = new DatagramPacket(buf, buf.length);
						socket.receive(packet);
						if(new String(packet.getData(), 0, packet.getLength()).equals("FRCServerRequest")) {
							String resp = ""+InetAddress.getLocalHost();
							buf = resp.getBytes();
							InetAddress packetAddress = packet.getAddress();
							packet = new DatagramPacket(buf, buf.length, packetAddress, Connection.BROADCAST_RESPONSE_PORT);
							socket.send(packet);
						}
					}
				} catch(IOException e) { e.printStackTrace(); }
			});
			t.start();
		} catch(IOException e) { e.printStackTrace(); }
	}
	public void close() {
		t = null;
		loop = false;
	}
}
