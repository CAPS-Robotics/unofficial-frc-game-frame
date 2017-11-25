package com.HiItsMe.unofficial_frc_game_frame.LAN;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by William Herron on 7/21/2017.
 * The class that facilitates easy use of LAN
 */
public class Connection {
    public static LANServer server;
    public static LANClient client;
    public static boolean connected;
    public static boolean isServer = false;
    public static final int DEFAULT_PORT = 46011;
    public static final int BROADCAST_PORT = 46010;
    public static final int BROADCAST_RESPONSE_PORT = 46012;
    public static final String BROADCAST_IP_GROUP = "230.0.0.1";
    //host a LAN game
    public static void host() {
        disconnect();
        isServer = true;
        server = new LANServer();
        while(!server.ready){}
        client = new LANClient("localhost");
	    connected = true;
    }
    //join a LAN game
    public static void join(String ip) {
        disconnect();
        isServer = false;
        boolean canConnect = true;
        try {
            Socket socket = new Socket(ip, DEFAULT_PORT);
            socket.close();
        } catch(IOException e) { canConnect = false; }
        if(canConnect) { client = new LANClient(ip); }
	    connected = canConnect;
    }
    //Return a list of servers on the network
    public static List<String[]> scanForServers() {
        List<String[]> out = new ArrayList<>();
        Thread t = null;
        List<Boolean> b = new ArrayList<>();
        b.add(true);
        try {
            DatagramSocket socket = new DatagramSocket(BROADCAST_RESPONSE_PORT);
            InetAddress group = InetAddress.getByName(BROADCAST_IP_GROUP);
            byte[] msg = "FRCServerRequest".getBytes();
            DatagramPacket packet = new DatagramPacket(msg, msg.length, group, BROADCAST_PORT);
            socket.send(packet);
            t = new Thread(()->{
                while(b.get(0)) {
                    try {
                        byte[] data = new byte[256];
                        DatagramPacket rpacket = new DatagramPacket(data, data.length);
                        socket.receive(rpacket);
                        out.add(new String(rpacket.getData(), 0, rpacket.getLength()).split("/"));
                    } catch(IOException e) { e.printStackTrace(); }
                }
            });
            t.start();
	        long startTime = System.currentTimeMillis();
	        long elapsedTime = 0L;
	        while (elapsedTime < 5000) {
		        elapsedTime = (new Date()).getTime() - startTime;
	        }
	        //threads need to die in a raging fire
	        b.set(0, false);
	        t = null;
	        socket.close();
        } catch(Exception e) { e.printStackTrace(); }
        return out;
    }
    //disconnect from LAN game
    public static void disconnect() {
    	connected = false;
        if(isServer) {
            if(server != null) {
                server.close();
                server = null;
            }
        }
        if(client != null) {
            client.close();
            client = null;
        }
    }
    //Send data to all other machines
    public static void sendData(Object data) {
        byte[] d = serialize(data);
        if(client != null) {
            client.send(d);
        }
    }
    //Passes any incoming data to the screen
    public static void receiveData(byte[] data) {
        FrameMain.screens.get(FrameMain.screen).incomingData(deserialize(data));
    }
    //convert objects to and from byte arrays to be sent through the network
    static byte[] serialize(Object data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        byte[] output = {};
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(data);
            out.flush();
            output = bos.toByteArray();
        } catch(IOException e) { e.printStackTrace(); } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }
    static Object deserialize(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;
        Object output = null;
        try {
            in = new ObjectInputStream(bis);
            output = in.readObject();
        } catch(Exception e) { e.printStackTrace(); } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }
}