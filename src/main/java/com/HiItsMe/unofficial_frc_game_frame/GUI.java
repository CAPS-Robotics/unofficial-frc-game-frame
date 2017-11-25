package com.HiItsMe.unofficial_frc_game_frame;

import com.HiItsMe.unofficial_frc_game_frame.EventListeners.FRCKeyListener;
import com.HiItsMe.unofficial_frc_game_frame.EventListeners.FRCMouseListener;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * Created by William Herron on 5/20/2017.
 * The actual display
 */
public class GUI extends Frame {
    public Graphics graphics = null;
    public Graphics2D g2d = null;
    BufferStrategy buffer;
    BufferedImage bi;
    public int[] screenRatio = {1256, 570};
    public double scaleSize = 1;
    public GraphicsConfiguration gc;
    public HashMap<String, Font> fonts = new HashMap<>();
    public GUI() {
        //Fullscreen and prep for constant redraw
        setIgnoreRepaint(true);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        //Set up canvas for drawing
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        canvas.setFocusable(false);
        canvas.setIgnoreRepaint(true);
        canvas.setSize(getWidth(), getHeight());
        add(canvas);
        validate();
        setVisible(true);
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //Init fonts
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gc = gd.getDefaultConfiguration();
        //Set up scaling
        if(getWidth() < getHeight()*screenRatio[0]/screenRatio[1]) {
            scaleSize = (double)getWidth()/screenRatio[0];
        } else {
            scaleSize = (double)getHeight()/screenRatio[1];
        }
        bi = gc.createCompatibleImage((int)(screenRatio[0]*scaleSize), (int)(screenRatio[1]*scaleSize));

        //Kill window if closed or esc pressed
        addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
            public void windowDeiconified(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowClosed(WindowEvent e) {}
            public void windowOpened(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
        });
        addKeyListener(new FRCKeyListener());
        //Send clicks to respective screen
        canvas.addMouseListener(new FRCMouseListener());
    }
    public void drw() {
        try {
            //Scale and draw screens then display in frame's center
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
            //Dispose of graphics for reuse
            if(graphics != null) {
                graphics.dispose();
            }
            if(g2d != null) {
                g2d.dispose();
            }
        }
    }
    public void drawCenteredText(Font font, String text, int x, int y) {
        g2d.setFont(font);
        x -= g2d.getFontMetrics(font).stringWidth(text)/2;
        y -= (g2d.getFontMetrics(font).getHeight()*(text.split("\\n").length))/2;
        g2d.drawString(text, x, y);
    }
    public void drawCenteredImage(BufferedImage image, int x, int y) {
        x -= image.getWidth()/2;
        y -= image.getHeight()/2;
        g2d.drawImage(image, x, y, null);
    }
    private void registerFont(GraphicsEnvironment graphicsEnvironment, String fontName, String backupFont) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("./src/main/resources/Fonts/" + fontName + ".ttf"));
            graphicsEnvironment.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font(backupFont, Font.PLAIN, 1);
        }
        fonts.put(fontName, font);
    }
    public Font font(String fontName, int fontStyle, int fontSize) {
        return fonts.get(fontName).deriveFont(fontStyle, fontSize);
    }
}
