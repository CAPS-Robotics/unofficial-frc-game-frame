package com.HiItsMe.unofficial_frc_game_frame;
import com.HiItsMe.unofficial_frc_game_frame.Screens.*;

import java.util.HashMap;
import java.util.Map;
import java.awt.*;

/**
 * Created by William Herron on 5/20/2017.
 * Runs everything, stores screens
 */
public class FrameMain {
    public static String screen = "";
    public static Map<String, FRCScreen> screens = new HashMap<String, FRCScreen>();

    public static GUI gui;

    public static void main(String[] args) {
        screens.put("Title", new Title());
        screens.put("Customize", new Customize());
        screens.put("Start", new Start());
        screens.put("Game", new Game());
        screens.put("Pause", new Pause());
        screens.put("End", new End());
        setScreen("Title");
        gui = new GUI();
        while(true) {
            gui.drw();
        }
    }

    public static void setScreen(String newScreen) {
        screen = newScreen;
        screens.get(newScreen).init();
    }
}
