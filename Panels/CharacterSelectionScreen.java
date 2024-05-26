/*
 * CHARACTER SELECTION SCREEN
 * Players can choose their fighters on this screen, once both players are connected to the same server
 */

package Panels;

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

public class CharacterSelectionScreen extends JPanel implements ActionListener {
    //Properties
    JLabel screenTitleLabel = new JLabel("Choose Your Fighter");
    JLabel player1NameLabel = new JLabel("Player 1");
    JLabel player2NameLabel = new JLabel("Player 2");
    //public JButton startGameButton1 = new JButton("Start Game");
    //public JButton startGameButton2 = new JButton("Start Game");
    JButton chooseScorpionButton = new JButton();
    JButton chooseSubZeroButton = new JButton();
    public boolean player1Ready, player2Ready = false;
    static String player1Choice, player2Choice = null;

    //Images in this Class:
    BufferedImage imgScorpSelection, imgSubZeroSelection, imgBackground;

    //Methods

    //paintComponent method here paints the images of the characters, and other parts of the screen:
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawRect(550-60, 180, 120, 240);
        g2d.drawRect(550+70, 180, 120, 240);
    }

    /**
     * Function to draw Scorpion if chosen on the Character Selection Screen. Position changes depending on who selects
     */
    private static void drawScorpion(Graphics g, int x, int y) {
        if (player1Choice == "Scorpion") {
            //TO-DO: Draw enlarged image of scorpion
        } else if (player2Choice == "Scorpion") {

        }
    } 

    /**
     * Function to draw SubZero if chosen on the Character Selection Screen. Position changes depending on who selects
     */
    private static void drawSubZero(Graphics g, int x, int y) {
        if (player1Choice == "Sub Zero") {
            //TO-DO: Draw enlarged image of sub zero on selection
        } else if (player2Choice == "Sub Zero") {

        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //Change button functions to be ready/return based on what the user selects
        //TO-DO: NEED LOGIC TO DETERMINE WHO IS PLAYER 1 AND 2 -- PLAYER 1 = HOST, ON THE LEFT OF THE SCREEN. PLAYER 2 = CLIENT, ON THE RIGHT OF THE SCREEN
        //For if statements, add something like: && currentPlayer == HOST or currentPlayer == CLIENT 
        //If this is possible, we don't need to put things like player1Ready or player2Ready, since players will only be able to choose their respective things.

        //For player 1:
        if (evt.getSource() == chooseScorpionButton && !player1Ready) { 
            player1Ready = true;
            player1Choice = "Scorpion";
        } else if (evt.getSource() == chooseSubZeroButton && !player1Ready) {
            player1Ready = true;
            player1Choice = "Sub Zero";
        }

        //For player 2:
        if (evt.getSource() == chooseScorpionButton && !player2Ready) {
            player2Ready = true;
            player1Choice = "Scorpion";
        } else if (evt.getSource() == chooseSubZeroButton && !player2Ready) {
            player2Ready = true;
            player1Choice = "Sub Zero";
        }
    }

    //Constructor
    public CharacterSelectionScreen() {
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        //Loading the images:
        //Try to read the image from both the jar file and local drive
        InputStream backgroundClass = this.getClass().getResourceAsStream("Images/TitleScreenBackground.jpeg");
        InputStream selectScorpClass = this.getClass().getResourceAsStream("Images/ScorpionSelection.png");
        InputStream selectSubClass = this.getClass().getResourceAsStream("Images/SubZeroSelection.png");
    
        if (backgroundClass != null) {
            try {
                imgBackground = ImageIO.read(backgroundClass);
                imgScorpSelection = ImageIO.read(selectScorpClass);
                imgSubZeroSelection = ImageIO.read(selectSubClass);
            } catch (IOException e) {
                System.out.println("Unable to read/load image from jar");
                e.printStackTrace();
            }
        } else { //If it can't be found on the jar, search it locally
            try {
                imgBackground = ImageIO.read(new File("Images/TitleScreenBackground.jpeg"));
                imgScorpSelection = ImageIO.read(new File("Images/ScorpionSelection.png"));
                imgSubZeroSelection = ImageIO.read(new File("Images/SubZeroSelection.png"));
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

        player1NameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        player1NameLabel.setForeground(Color.BLACK);
        player1NameLabel.setSize(700, 30);
        player1NameLabel.setLocation(150, 30);
        this.add(player1NameLabel);

        player2NameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        player2NameLabel.setForeground(Color.BLACK);
        player2NameLabel.setSize(1050, 30);
        player2NameLabel.setLocation(1000, 30);
        this.add(player2NameLabel);

        //Button formatting:
        chooseScorpionButton.setSize(120, 240);
        chooseScorpionButton.setLocation(550-60, 180);
        chooseScorpionButton.addActionListener(this);
        chooseScorpionButton.setOpaque(false); // Make the button transparent
        chooseScorpionButton.setContentAreaFilled(false); 
        chooseScorpionButton.setBorderPainted(false); 
        chooseScorpionButton.setForeground(Color.WHITE);
        this.add(chooseScorpionButton);

        chooseSubZeroButton.setSize(120, 240);
        chooseSubZeroButton.setLocation(620, 180);
        chooseSubZeroButton.addActionListener(this);
        chooseSubZeroButton.setOpaque(false); // Make the button transparent
        chooseSubZeroButton.setContentAreaFilled(false); 
        chooseSubZeroButton.setBorderPainted(false); 
        chooseSubZeroButton.setForeground(Color.WHITE);
        this.add(chooseSubZeroButton);

        this.repaint();
    }
}
