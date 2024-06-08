package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * This class stores the object for the IceBall, which spawns on SubZero's special move
 */
public class IceBall {
    //Properties
    /**
     * Stores the BufferedImages for the IceBalls
     */
    public BufferedImage IceBallLeft = null, IceBallRight = null;

    /**
     * The x-position of the IceBall. Used to draw IceBall image position and calculate IceBall hitbox
     */
    public int iceBallX = 0;

    /**
     * Boolean to determine whether or not the IceBall should be drawn
     */
    public boolean toRender = false;

    /**
     * Final int values to store the width and height of the IceBall image
     */
    public final int WIDTH = 135, HEIGHT = 70;

    //Methods

    //Constructor
    public IceBall() {
        InputStream iceBallLeftClass = this.getClass().getResourceAsStream("src/assets/IceBallLeft.png");
        InputStream iceBallRightClass = this.getClass().getResourceAsStream("src/assets/IceBallRight.png");

        if (iceBallLeftClass != null && iceBallRightClass != null) {
            try { //Jar
                IceBallLeft = ImageIO.read(iceBallLeftClass);
                IceBallRight = ImageIO.read(iceBallRightClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try { //Local
                IceBallLeft = ImageIO.read(new File("src/assets/IceBallLeft.png"));
                IceBallRight = ImageIO.read(new File("src/assets/IceBallRight.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
    }

}
