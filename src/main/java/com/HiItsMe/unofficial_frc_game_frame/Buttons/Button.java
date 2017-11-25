package com.HiItsMe.unofficial_frc_game_frame.Buttons;

/**
 * Created by William Herron on 5/20/2017.
 * Superclass to all Buttons
 */
public class Button {
    public int bwidth = 253;
    public int bheight = 99;
    public int x;
    public int y;
    public void trigger() {}
    public void draw() {}
    public void checkClick(int cx, int cy) {
        if(cx > x - bwidth/2 && cy > y - bheight/2 && cx < x + bwidth/2 && cy < y + bheight/2) {
            trigger();
        }
    }
}