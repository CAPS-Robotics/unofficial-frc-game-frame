package com.HiItsMe.unofficial_frc_game_frame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Created by William Herron on 5/20/2017.
 */
public class GUI extends Frame {
    public Graphics graphics = null;
    public Graphics2D g2d = null;
    BufferStrategy buffer;
    BufferedImage bi;
    public GUI() {
        setIgnoreRepaint( true );
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        Canvas canvas = new Canvas();
        canvas.setIgnoreRepaint( true );
        canvas.setSize( getWidth(), getHeight() );
        add( canvas );
        setVisible( true );
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        bi = gc.createCompatibleImage(getWidth(), getHeight());

        //Kill window if closed or esc pressed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 27) {
                    dispose();
                    System.exit(0);
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });

    }
    public void drw() {
        try {
            g2d = bi.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth()-1, getHeight()-1);
            FrameMain.screens.get(FrameMain.screen).draw();
            graphics = buffer.getDrawGraphics();
            graphics.drawImage(bi, 0, 0, null);
            if(!buffer.contentsLost()) {
                buffer.show();
            }
            Thread.yield();
        } finally {
            if( graphics != null ) {
                graphics.dispose();
            }
            if( g2d != null ) {
                g2d.dispose();
            }
        }
    }
}
