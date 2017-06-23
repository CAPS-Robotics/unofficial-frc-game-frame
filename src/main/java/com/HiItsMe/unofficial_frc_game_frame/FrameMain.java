package com.HiItsMe.unofficial_frc_game_frame;
import com.HiItsMe.unofficial_frc_game_frame.Screens.*;

import java.util.HashMap;
import java.util.Map;
import java.awt.*;

/**
 * Created by William Herron on 5/20/2017.
 * Runs everything, stores screens
 *
 * I assume this is the first class you're reading.
 * If not, it should be.
 * Because of that, I'll talk about the project here.
 * This is a top-down video game based on FIRST Robotics Challenge.
 * That's pretty much all there is to say.
 *
 * BTW, my commenting is absolutely terrible.
 * I apologize to anyone trying to make sense of this garbage.
 * If you really want to know what something is or does, either
 *     -Just screw with it until you break things
 *     -Message me on GitHub or Chief Delphi.
 *      I'll try to get back to you ASAP.
 *      I'm HiItsMe on Chief Delphi, and HiItsMeHaHa on GitHub.
 */
public class FrameMain {
    public static String screen = "";
    public static Map<String, FRCScreen> screens = new HashMap<String, FRCScreen>();

    public static GUI gui;

    public static void main(String[] args) {
        gui = new GUI();
        screens.put("Title", new Title());
        screens.put("Customize", new Customize());
        screens.put("Start", new Start());
        screens.put("Game", new Game());
        screens.put("Pause", new Pause());
        screens.put("End", new End());
        setScreen("Title");
        while(true) {
            gui.drw();
        }
    }

    public static void setScreen(String newScreen) {
        screen = newScreen;
        screens.get(newScreen).init();
    }
}
