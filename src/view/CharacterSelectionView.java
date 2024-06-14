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
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.GameState;

/**
 * JPanel to display the CharacterSelectionView when the game initially begins in the HomeScreen
 */
public class CharacterSelectionView extends JPanel implements ActionListener {
    //Properties
    private JLabel screenTitleLabel = new JLabel("Choose Your Fighter");
    private JLabel hostNameLabel = new JLabel("Player 1");
    private JLabel clientNameLabel = new JLabel("Player 2");
    private JLabel player1ChoiceLabel = new JLabel();
    private JLabel player2ChoiceLabel = new JLabel();
    private JLabel startingGameLabel = new JLabel();

    private JButton chooseScorpionButton = new JButton();
    private JButton chooseSubZeroButton = new JButton();
    private GameState state;
    private GameView gameView;
    private String hostChoice = "", clientChoice = "";
    private boolean hostReady = false, clientReady = false;
    //Images in this Class:
    private static BufferedImage imgScorpSelection, imgSubZeroSelection, imgEnlargedScorp, imgEnlargedSub; //Small images = 120x240, Enlarged images = 300x600

    //Methods:

    /**
     * paintComponent method here paints the images of the characters, and other parts of the screen:
     * @param g Graphics to paint images
     */
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

        System.out.println("Is player 1 null? "+(state.player1 == null));
        System.out.println("Is player 2 null? "+(state.player2 == null));

        if (state.player1 == null || state.player2 == null) { return; }
        if (state.player1.fighter == null || state.player2.fighter == null) { return; }
        // System.out.println(state.player1.fighter.name);
        // System.out.println(state.player2.fighter.name);

        hostNameLabel.setText(state.player1.name);
        clientNameLabel.setText(state.player2.name);

        //Based on the selection, draw an enlarged image of who was selected. 
        //Read the text sent from SSM, and update based on the SSM sendText choice
        if (state.player1.fighter.name.equals("Scorpion")) {
            drawScorpion(g, 80, 60);
            player1ChoiceLabel.setText("Scorpion");
        } 
        
        if (state.player2.fighter.name.equals("Scorpion")) {
            drawScorpion(g, 880, 60);
            player2ChoiceLabel.setText("Scorpion");
        }

        if (state.player1.fighter.name.equals("Subzero")) { 
            drawSubZero(g, 80, 90);
            player1ChoiceLabel.setText("Sub Zero");
        } 
        
        if (state.player2.fighter.name.equals("Subzero")) {
            drawSubZero(g, 880, 90);
            player2ChoiceLabel.setText("Sub Zero");
        }

        //System.out.println("host ready? "+hostReady);
        //System.out.println("client ready? "+clientReady);

        //Start the game if both players done choosing
        if (state.player1.fighter != null && state.player2.fighter != null) {     
            startingGameLabel.setText("Starting game...");

            //Set the original positions for the host and client
            state.player1.currentX = 400 - this.state.player1.fighter.WIDTH;
            state.player1.currentY = 680 - this.state.player1.fighter.HEIGHT;
            state.player2.currentX = 1280 - 400;
            state.player2.currentY = 680 - this.state.player2.fighter.HEIGHT;

            state.player1.currentAnimationImg = state.player1.fighter.idleLeft;
            state.player2.currentAnimationImg = state.player2.fighter.idleRight;

            state.player1.currentAction = "idle";
            state.player2.currentAction = "idle";

            //Show game panel
            gameView = new GameView(state);
            MainView.panel.add(gameView, "gameView");
            MainView.cardLayout.show(MainView.panel, "gameView");
            //startGameTimer(); 
        }

        this.repaint();
    }

    //Start game after loading stuff for a bit
    private void startGameTimer() {
        Timer startGameTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameView = new GameView(state);
                MainView.panel.add(gameView, "gameView");
                MainView.cardLayout.show(MainView.panel, "gameView");
                repaint();
            }
        });
        startGameTimer.setRepeats(false);
        startGameTimer.start();
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


    /**
     * actionPerfomed to listen for button pressed
     * @param evt ActionEvent initialization
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        //Change button functions to be ready/return based on what the user selects

        //For player 1 (Host):
        if (evt.getSource() == chooseScorpionButton) {
            if (!hostReady && state.currentPlayer == state.player1) {
                hostReady = true;
                hostChoice = "Host,"+state.player1.name+",Scorpion";
                System.out.println(state.player1.name +" chose Scorpion");
                state.player1.chooseFighter("Scorpion");
                state.ssm.sendText(hostChoice);
            } else if (!clientReady && state.currentPlayer == state.player2) {
                clientReady = true;
                clientChoice = "Client,"+state.player2.name +",Scorpion";
                System.out.println(state.player2.name +" chose Scorpion");
                state.player2.chooseFighter("Scorpion");
                state.ssm.sendText(clientChoice);         
            }
        } else if (evt.getSource() == chooseSubZeroButton) {
            if (!hostReady && state.currentPlayer == state.player1) {
                hostReady = true;
                hostChoice = "Host,"+state.player1.name+",Subzero";
                System.out.println(state.player1.name +" chose Subzero");
                state.player1.chooseFighter("Subzero");
                state.ssm.sendText(hostChoice);
            } else if (!clientReady && state.currentPlayer == state.player2) {
                clientReady = true;
                clientChoice = "Client,"+state.player2.name+",Subzero";
                System.out.println(state.player2.name +" chose Sub");
                state.player2.chooseFighter("Subzero");
                state.ssm.sendText(clientChoice);
            }
        }
    }
 
 
    /**
     * Constructor to intialize CharacterSelectionView with GameState to update new Players and their chosen fighters
     */
     public CharacterSelectionView(GameState state) {
        super();

        this.state = state;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        //Loading the images:
        //Try to read the image from both the jar file and local drive
        InputStream selectScorpClass = this.getClass().getResourceAsStream("src/assets/ScorpionSelection.png");
        InputStream selectSubClass = this.getClass().getResourceAsStream("src/assets/SubZeroSelection.png");
        InputStream enlargedScorpClass = this.getClass().getResourceAsStream("src/assets/ScorpionEnlarged.png");
        InputStream enlargedSubClass = this.getClass().getResourceAsStream("src/assets/SubZeroEnlarged.png");

        if (selectScorpClass != null && selectSubClass != null) {
            try {
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
        hostNameLabel.setLocation(160, 30);
        this.add(hostNameLabel);

        clientNameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        clientNameLabel.setForeground(Color.BLACK);
        clientNameLabel.setSize(1050, 30);
        clientNameLabel.setLocation(1000, 30);
        this.add(clientNameLabel);

        player1ChoiceLabel.setFont(new Font("Cambria", Font.PLAIN,16));
        player1ChoiceLabel.setForeground(Color.BLACK);
        player1ChoiceLabel.setSize(700, 30);
        player1ChoiceLabel.setLocation(160, 60);
        this.add(player1ChoiceLabel);

        player2ChoiceLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
        player2ChoiceLabel.setForeground(Color.BLACK);
        player2ChoiceLabel.setSize(1050, 30);
        player2ChoiceLabel.setLocation(1000, 60);
        this.add(player2ChoiceLabel);

        startingGameLabel.setFont(new Font("Cambria", Font.BOLD, 22));
        startingGameLabel.setForeground(Color.BLACK);
        startingGameLabel.setBounds(550-40, 500, 200, 50);
        startingGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startingGameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(startingGameLabel);

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
    }
}
