package com.HiItsMe.unofficial_frc_game_frame.Buttons;

import com.HiItsMe.unofficial_frc_game_frame.FrameMain;

import org.w3c.dom.*;
import javax.imageio.ImageIO;
import javax.xml.*;
import javax.xml.transform.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by William Herron on 5/20/2017.
 * Saves the robot you customize
 */
public class SaveButton extends Button {
    BufferedImage img;
    public BufferedImage saveImage;
    public String[] attributeNames = {"Speed", "Shooter", "Drivetrain", "Autonomous"};
    public int[] attributeValues = new int[4];
    public SaveButton(int X, int Y) {
        //Initialize button location and image
        x = X;
        y = Y;
        img = null;
        try {
            img = ImageIO.read(new File("./src/main/resources/Images/SaveButton.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
    @Override
    public void trigger() {
        //Save XML properties
        int robotNum = 0;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("./src/main/resources/Robots/Robots.xml");
            Node robots = doc.getDocumentElement();
            robots.normalize();
            NodeList robotList = robots.getChildNodes();
            robotNum = robotList.getLength();
            Element robot = doc.createElement("Robot"+robotNum);
            for(int i = 0; i < 4; i++) {
                Element attribute = doc.createElement(attributeNames[i]);
                attribute.appendChild(doc.createTextNode(""+attributeValues[i]));
                robot.appendChild(attribute);
            }
            robots.appendChild(robot);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./src/main/resources/Robots/Robots.xml"));
            transformer.transform(source, result);
        } catch(Exception e) { e.printStackTrace(); }
        /* Save robot image
         * this is unnecessary but provides a slight efficiency boost during the game and allows me to make and save cool robots
         */
        try {
            File outputFile = new File("./src/main/resources/Robots/Robot"+robotNum+".png");
            ImageIO.write(saveImage, "png", outputFile);
        } catch(Exception e) { e.printStackTrace(); }
        FrameMain.setScreen("Title");
    }
    @Override
    public void draw() { FrameMain.gui.g2d.drawImage(img, x, y, null); }
}
