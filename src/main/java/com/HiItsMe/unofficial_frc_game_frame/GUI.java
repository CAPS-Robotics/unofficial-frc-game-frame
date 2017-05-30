package com.HiItsMe.unofficial_frc_game_frame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Arrays;

/**
 * Created by William Herron on 5/20/2017.
 */
public class GUI extends Frame {
    public Graphics graphics = null;
    public Graphics2D g2d = null;
    BufferStrategy buffer;
    BufferedImage bi;
    public int[] screenRatio = {1256, 570};
    public double scaleSize = 1.000000000000000;
    public GUI() {
        setIgnoreRepaint(true);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        Canvas canvas = new Canvas();
        canvas.setFocusable(false);
        canvas.setIgnoreRepaint(true);
        canvas.setSize(getWidth(), getHeight());
        add(canvas);
        validate();
        setVisible(true);
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        if(getWidth() < getHeight()*screenRatio[0]/screenRatio[1]) {
            scaleSize = (double)getWidth()/screenRatio[0];
        } else {
            scaleSize = (double)getHeight()/screenRatio[1];
        }
        bi = gc.createCompatibleImage((int)(screenRatio[0]*scaleSize), (int)(screenRatio[1]*scaleSize));

        //Kill window if closed or esc pressed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        addKeyListener(new FRCKeyListener());
        canvas.addMouseListener(new FRCMouseListener());
    }
    public void drw() {
        try {
            g2d = bi.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth()-1, getHeight()-1);
            g2d.scale(scaleSize, scaleSize);
            FrameMain.screens.get(FrameMain.screen).draw();
            graphics = buffer.getDrawGraphics();
            graphics.drawImage(bi, (int)((getWidth()-(screenRatio[0]*scaleSize))/2), (int)((getHeight()-(screenRatio[1]*scaleSize))/2), null);
            if(!buffer.contentsLost()) {
                buffer.show();
            }
            Thread.yield();
        } finally {
            if(graphics != null) {
                graphics.dispose();
            }
            if(g2d != null) {
                g2d.dispose();
            }
        }
    }
}
