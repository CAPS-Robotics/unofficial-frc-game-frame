package com.HiItsMe.unofficial_frc_game_frame.LAN;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


/**
 * Created by William Herron on 8/21/2017.
 * Connects to a preexisting server
 */
public class LANClient {
    Socket socket;
    Thread input;
    boolean run = true;
    public LANClient(String hostname) {
        try {
            socket = new Socket(hostname, Connection.DEFAULT_PORT);
        } catch(IOException e) { e.printStackTrace(); }
        input = new Thread(()->{
            try(InputStream in = socket.getInputStream()) {
                while(run) {
                    int av = in.available();
                    if(av > 0) {
                        byte[] out = new byte[av];
                        in.read(out);
                        Connection.receiveData(out);
                    }
                }
                socket.close();
            } catch(IOException e) { e.printStackTrace(); }
        });
        input.start();
    }
    public void send(byte[] data) {
        try {
            socket.getOutputStream().write(data);
        } catch(IOException e) { e.printStackTrace(); }
    }
    //Die, threads.
    public void close() {
        run = false;
        input = null;
    }
}