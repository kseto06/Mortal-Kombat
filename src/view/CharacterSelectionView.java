/*
 * CHARACTER SELECTION SCREEN
 * Players can choose their fighters on this screen, once both players are connected to the same server
 */


package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CharacterSelectionView extends JPanel implements ActionListener {
    //Properties
    JLabel screenTitleLabel = new JLabel("Choose Your Fighter");
    JLabel hostNameLabel = new JLabel("Player 1");
    JLabel clientNameLabel = new JLabel("Player 2");
    //public JButton startGameButton1 = new JButton("Start Game");
    //public JButton startGameButton2 = new JButton("Start Game");
    JButton chooseScorpionButton = new JButton();
    JButton chooseSubZeroButton = new JButton();
    public boolean hostReady, clientReady = false;
    boolean chooseScorpionPressed, chooseSubZeroPressed = false;
    static String hostChoice, clientChoice = "";
    public String userType;

    //Images in this Class:
    static BufferedImage imgScorpSelection, imgSubZeroSelection, imgEnlargedScorp, imgEnlargedSub, imgBackground; //Small o,ages = 120x240, Enlarged images = 300x600

    //Timer, allows for drawing the chosen character
    /**
     * The timer that will control the animation, going off at 60fps
     */
    Timer timer = new Timer(1000/60, this);

    //Methods:

    //paintComponent method here paints the images of the characters, and other parts of the screen:
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw the "Select Character Buttons" on the Screen
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawRect(550-60, 180, 120, 240);
        g2d.drawRect(550+70, 180, 120, 240);
        g.drawImage(imgScorpSelection, 550-60, 180, this);
        g.drawImage(imgSubZeroSelection, 620, 180, this);


        //Based on the selection, draw an enlarged image of who was selected:
        if (hostChoice == "Scorpion") {
            drawScorpion(g, 100, 60);
        } else if (clientChoice == "Scorpion") {
            drawScorpion(g, 900, 60);
        }

        if (hostChoice == "Sub Zero") { 
            drawSubZero(g, 100, 60);
        } else if (clientChoice == "Sub Zero") {
            drawSubZero(g, 900, 60);
        }
    }


    /**
     * Function to draw larger image of Scorpion if chosen on the Character Selection Screen. Position changes depending on who selects
     */
    private static void drawScorpion(Graphics g, int x, int y) {
        g.drawImage(imgEnlargedScorp, x, y, null);
    }

    /**
     * Function to draw larger image of SubZero if chosen on the Character Selection Screen. Position changes depending on who selects
     */
    private static void drawSubZero(Graphics g, int x, int y) {
        g.drawImage(imgEnlargedSub, x, y, null);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
        //Change button functions to be ready/return based on what the user selects
        //TO-DO: NEED LOGIC TO DETERMINE WHO IS PLAYER 1 AND 2 -- PLAYER 1 = HOST, ON THE LEFT OF THE SCREEN. PLAYER 2 = CLIENT, ON THE RIGHT OF THE SCREEN
        //For if statements, add something like: && currentPlayer == HOST or currentPlayer == CLIENT
        //If this is possible, we don't need to put things like hostReady or clientReady, since players will only be able to choose their respective things.

        //Timer (keeping track of event listeners that happen over a period of time)
        if (evt.getSource() == timer) {
            this.repaint();
        }

        //For player 1 (Host):
        if (evt.getSource() == chooseScorpionButton && !hostReady && userType == "Host") {
            hostReady = true;
            hostChoice = "Scorpion";
            chooseScorpionPressed = true;
            System.out.println(userType+" chose Scorpion");
        } else if (evt.getSource() == chooseSubZeroButton && !hostReady && userType == "Host") {
            hostReady = true;
            hostChoice = "Sub Zero";
            chooseScorpionPressed = true;
            System.out.println(userType+" chose Scorpion");
        }

        //For player 2 (Client):
        if (evt.getSource() == chooseScorpionButton && !clientReady && userType == "Client") {
            clientReady = true;
            clientChoice = "Scorpion";
            chooseSubZeroPressed = true;
            System.out.println(userType+" chose Sub");
        } else if (evt.getSource() == chooseSubZeroButton && !clientReady && userType == "Client") {
            clientReady = true;
            clientChoice = "Sub Zero";
            chooseSubZeroPressed = true;
            System.out.println(userType+" chose Sub");
        }
    }
 
 
     //Constructor
     public CharacterSelectionView() {
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));


        //Loading the images:
        //Try to read the image from both the jar file and local drive
        InputStream backgroundClass = this.getClass().getResourceAsStream("src/assets/TitleScreenBackground.jpeg");
        InputStream selectScorpClass = this.getClass().getResourceAsStream("src/assets/ScorpionSelection.png");
        InputStream selectSubClass = this.getClass().getResourceAsStream("src/assets/SubZeroSelection.png");
        InputStream enlargedScorpClass = this.getClass().getResourceAsStream("src/assets/ScorpionEnlarged.png");
        InputStream enlargedSubClass = this.getClass().getResourceAsStream("src/assets/SubZeroEnlarged.png");

        if (selectScorpClass != null && selectSubClass != null) {
            try {
                imgBackground = ImageIO.read(backgroundClass);
                imgScorpSelection = ImageIO.read(selectScorpClass);
                imgSubZeroSelection = ImageIO.read(selectSubClass);
                imgEnlargedScorp = ImageIO.read(enlargedScorpClass);
                imgEnlargedSub = ImageIO.read(enlargedSubClass);
            } catch (IOException e) {
                System.out.println("Unable to read/load image from jar");
                e.printStackTrace();
            }
        } else { //If it can't be found on the jar, search it locally
            try {
                imgBackground = ImageIO.read(new File("src/assets/TitleScreenBackground.jpeg"));
                imgScorpSelection = ImageIO.read(new File("src/assets/ScorpionSelection.png"));
                imgSubZeroSelection = ImageIO.read(new File("src/assets/SubZeroSelection.png"));
                imgEnlargedScorp = ImageIO.read(new File("src/assets/ScorpionEnlarged.png"));
                imgEnlargedSub = ImageIO.read(new File("src/assets/SubZeroEnlarged.png"));
            } catch (IOException e) {
                System.out.println("Unable to read/load image");
                e.printStackTrace();
            }
        }

        //Label formatting:
        screenTitleLabel.setFont(new Font("Cambria", Font.BOLD, 30));
        screenTitleLabel.setForeground(Color.BLACK);
        screenTitleLabel.setSize(700, 50);
        screenTitleLabel.setLocation(640-160, 10);
        this.add(screenTitleLabel);

        hostNameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        hostNameLabel.setForeground(Color.BLACK);
        hostNameLabel.setSize(700, 30);
        hostNameLabel.setLocation(150, 30);
        this.add(hostNameLabel);

        clientNameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        clientNameLabel.setForeground(Color.BLACK);
        clientNameLabel.setSize(1050, 30);
        clientNameLabel.setLocation(1000, 30);
        this.add(clientNameLabel);

        //Button formatting:
        chooseScorpionButton.setSize(120, 240);
        chooseScorpionButton.setLocation(550-60, 180);
        chooseScorpionButton.addActionListener(this);
        chooseScorpionButton.setOpaque(false); // Make the button transparent
        chooseScorpionButton.setContentAreaFilled(false);
        chooseScorpionButton.setBorderPainted(false);
        chooseScorpionButton.setForeground(Color.WHITE);
        this.add(chooseScorpionButton);
        chooseScorpionButton.addActionListener(this);

        chooseSubZeroButton.setSize(120, 240);
        chooseSubZeroButton.setLocation(620, 180);
        chooseSubZeroButton.addActionListener(this);
        chooseSubZeroButton.setOpaque(false); // Make the button transparent
        chooseSubZeroButton.setContentAreaFilled(false);
        chooseSubZeroButton.setBorderPainted(false);
        chooseSubZeroButton.setForeground(Color.WHITE);
        this.add(chooseSubZeroButton);
        chooseSubZeroButton.addActionListener(this);

        //Start timer
        timer.start();
    }
}
