package com.HiItsMe.unofficial_frc_game_frame.Screens;

import com.HiItsMe.unofficial_frc_game_frame.Buttons.*;

/**
 * Created by William Herron on 5/20/2017.
 * The superclass to all Screens
 */
public class FRCScreen {
    public void init() {}
    public void draw() {}
    public Button[] buttons;
    public void clickButtons(int cx, int cy) {
        for(Button button : buttons) {
            button.checkClick(cx, cy);
        }
    }
    public void keyDown(int key) {}
    public void keyUp(int key) {}
}
