package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class IceBall {
    //Properties
    public BufferedImage IceBallLeft = null, IceBallRight = null;
    public int iceBallX = 0;
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
