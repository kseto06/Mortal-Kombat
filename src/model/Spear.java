package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * This class stores the object for the Spear, which spawns on Scorpion's special move
 */
public class Spear {
    //Properties
    /**
     * Stores the BufferedImages for the Spears
     */
    public BufferedImage SpearLeft = null, SpearRight = null;

    /**
     * The x-position of the Spear. Used to draw Spear image position and calculate Spear hitbox
     */
    public int spearX = 0;

    /**
     * Boolean to determine whether or not the Spear should be drawn
     */
    public boolean toRender = false;

    /**
     * Final int values to store the width and height of the Spear image
     */
    public final int WIDTH = 233, HEIGHT = 50;

    //Methods

    /**
     * Constructor to initalize Scorpion's Spear
     */
    public Spear() {
        InputStream spearLeftClass = this.getClass().getResourceAsStream("src/assets/SpearLeft.png");
        InputStream spearRightClass = this.getClass().getResourceAsStream("src/assets/SpearRight.png");

        if (spearLeftClass != null && spearRightClass != null) {
            try { //Jar
                SpearLeft = ImageIO.read(spearLeftClass);
                SpearRight = ImageIO.read(spearRightClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try { //Local
                SpearLeft = ImageIO.read(new File("src/assets/SpearLeft.png"));
                SpearRight = ImageIO.read(new File("src/assets/SpearRight.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
    }

}
