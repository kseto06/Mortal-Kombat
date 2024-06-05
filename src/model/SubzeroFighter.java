package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class SubzeroFighter extends Fighter {
    // Properties
    private long specialLastUsed = 0; // TODO: set to value
    public String name = "Subzero";

    // Methods
    public void specialMove(Fighter opponent) {
        // TO-DO: Implement attack method
    }

        //TODO: InputStream shit
    public SubzeroFighter() {
        super();

        //Set width and height of generic fighter class
        WIDTH = 102; //(px) of idle image
        HEIGHT = 158; //(px) of idle image

        //Try to read the image from both the jar file and local drive
        InputStream idleLeftClass = this.getClass().getResourceAsStream("src/assets/subzeroIdleLeft.png");
        InputStream idleRightClass = this.getClass().getResourceAsStream("src/assets/subzeroIdleRight.png");
        InputStream punchLeftClass = this.getClass().getResourceAsStream("src/assets/subzeroPunchLeft.png");
        InputStream punchRightClass = this.getClass().getResourceAsStream("src/assets/subzeroPunchRight.png");
        InputStream kickLeftClass = this.getClass().getResourceAsStream("src/assets/subzeroKickLeft.png");
        InputStream kickRightClass = this.getClass().getResourceAsStream("src/assets/subzeroKickRight.png");
        InputStream uppercutLeftClass = this.getClass().getResourceAsStream("src/assets/subzeroUppercutLeft.png");
        InputStream uppercutRightClass = this.getClass().getResourceAsStream("src/assets/subzeroUppercutRight.png");
        InputStream specialLeftClass = this.getClass().getResourceAsStream("src/assets/subzeroSpecialLeft.png");
        InputStream specialRightClass = this.getClass().getResourceAsStream("src/assets/subzeroSpecialRight.png");

        if (punchLeftClass != null && punchRightClass != null && kickLeftClass != null && kickRightClass != null && uppercutLeftClass != null && uppercutRightClass != null && specialLeftClass != null && specialRightClass != null) {
            try {
                idleLeft = ImageIO.read(idleLeftClass);
                idleRight = ImageIO.read(idleRightClass);
                punchLeft = ImageIO.read(punchLeftClass);
                punchRight = ImageIO.read(punchRightClass);
                kickLeft = ImageIO.read(kickLeftClass);
                kickRight = ImageIO.read(kickRightClass);
                uppercutLeft = ImageIO.read(uppercutLeftClass);
                uppercutRight = ImageIO.read(uppercutRightClass);
                specialLeft = ImageIO.read(specialLeftClass);
                specialRight = ImageIO.read(specialRightClass);
            } catch (IOException e) {
                System.out.println("Unable to read/load image from jar");
                e.printStackTrace();
            }
        } else { //If it can't be found on the jar, search it locally
            try {
                idleLeft = ImageIO.read(new File("src/assets/subzeroIdleLeft.png"));
                idleRight = ImageIO.read(new File("src/assets/subzeroIdleRight.png"));
                punchLeft = ImageIO.read(new File("src/assets/subzeroPunchLeft.png"));
                punchRight = ImageIO.read(new File("src/assets/subzeroPunchRight.png"));
                kickLeft = ImageIO.read(new File("src/assets/subzeroKickLeft.png"));
                kickRight = ImageIO.read(new File("src/assets/subzeroKickRight.png"));
                uppercutLeft = ImageIO.read(new File("src/assets/subzeroUppercutLeft.png"));
                uppercutRight = ImageIO.read(new File("src/assets/subzeroUppercutRight.png"));
                specialLeft = ImageIO.read(new File("src/assets/subzeroSpecialLeft.png"));
                specialRight = ImageIO.read(new File("src/assets/subzeroSpecialRight.png"));

            } catch (IOException e) {
                System.out.println("Unable to read/load image");
                e.printStackTrace();
            }
        }

    }
}
