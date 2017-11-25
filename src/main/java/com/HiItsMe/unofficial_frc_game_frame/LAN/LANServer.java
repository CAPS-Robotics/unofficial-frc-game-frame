package com.HiItsMe.unofficial_frc_game_frame.LAN;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


/**
 * Created by William Herron on 7/21/2017.
 * Runs the server to connect other computers via LAN
 */
public class LANServer {
    ArrayList<ServerThread> clients = new ArrayList<>();
    Thread clientChecker;
    BroadcastReceiver broadcastReceiver;
    boolean run = true;
    public boolean ready = false;
    public LANServer() {
        broadcastReceiver = new BroadcastReceiver();
        ready = false;
        clientChecker = new Thread(()->{
            try (ServerSocket serverSocket = new ServerSocket(Connection.DEFAULT_PORT)) {
                while(run) {
                    ready = serverSocket.isBound();
                    clients.add(new ServerThread(serverSocket.accept()));
                    clients.get(clients.size()-1).run();
                }
                serverSocket.close();
            } catch(IOException e) { e.printStackTrace(); }
        });
        clientChecker.start();
    }
    public void send(byte[] data) {
        for(ServerThread client : clients) {
            client.send(data);
        }
    }
    //this entire method is redundant as hell but screw threads seriously
    public void close() {
        broadcastReceiver.close();
        broadcastReceiver = null;
        run = false;
        clientChecker = null;
        for(int i = 0; i < clients.size(); i++) {
            clients.get(i).run = false;
            clients.set(i, null);
            clients.remove(i);
        }
    }
}
