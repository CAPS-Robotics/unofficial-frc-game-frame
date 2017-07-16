package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;
import com.HiItsMe.unofficial_frc_game_frame.Screens.Customize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 6/7/2017.
 * Used on the Customize screen to upgrade your robot
 */
public class UpgradeSelector {
    BufferedImage[] imgs = new BufferedImage[3];
    private int _tiers, _x, _y, _def;
    public int tier;
    public UpgradeSelector(int tiers, int x, int y, int def) {
        _tiers = tiers; _x = x; _y = y; _def = def;
        tier = def;
        try {
            imgs[0] = ImageIO.read(new File("./src/main/resources/Images/Box0.png"));
            imgs[1] = ImageIO.read(new File("./src/main/resources/Images/Box1.png"));
            imgs[2] = ImageIO.read(new File("./src/main/resources/Images/Box2.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    public void draw() {
        for(int i = 0; i < _tiers; i++) {
            if(i < _def) {
                FrameMain.gui.drawCenteredImage(imgs[2], _x + (i * 25), _y);
            } else if(i < tier) {
                FrameMain.gui.drawCenteredImage(imgs[1], _x + (i * 25), _y);
            } else {
                FrameMain.gui.drawCenteredImage(imgs[0], _x + (i * 25), _y);
            }
        }
    }
    public void checkClick(int cx, int cy) {
        for(int i = _def; i < _tiers; i++) {
            if(cx > _x + (i * 25) - 10 && cx < _x + (i * 25) + 10 && cy > _y - 10 && cy < _y + 10) {
                int ptier = tier;
                if(tier == i + 1) {
                    tier = i;
                } else { tier = i + 1; }
                Customize scr = (Customize)(FrameMain.screens.get(FrameMain.screen));
                if(tier - ptier <= scr.assets) {
                    scr.assets -= (tier - ptier);
                } else {
                    tier = ptier + scr.assets;
                    scr.assets = 0;
                }
            }
        }
    }
}
