/*
 * THE VIEW CLASS CONTAINS CODE FOR FRONTEND PROGRAM -- ANIMATIONS AND SOME GAME LOGIC (i.e. hitboxes)
 */

import Panels.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel implements ActionListener, KeyListener {
    //Properties
    JFrame frame = new JFrame("View Testing");
    CardLayout cardLayout = new CardLayout();
    HomeScreen homeScreen = new HomeScreen();
    CharacterSelectionScreen characterSelectionScreen = new CharacterSelectionScreen();
    Model model = new Model();
    static String[] playerList = new String[2];


    //Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        //If the user presses the host button on the home screen, open SSM to host their game
        if (evt.getSource() == homeScreen.hostButton) {
            //One person has joined -- manipulate the CharacterSelectionScreen to have a "waiting for opponent"
            model.hostServer();

        } else if (evt.getSource() == homeScreen.joinButton) {
            //Two people now in-game, show the Character Selection Screen 
            model.clientServer();
            cardLayout.show(this, "characterSelectionScreen");
        
        } else if (evt.getSource() == homeScreen.helpButton) {
            //TO-DO: Interactive HelpScreen
        
        } else if (characterSelectionScreen.player1Ready == true && characterSelectionScreen.player2Ready == true) {
            //Two people done selecting, start game

        } 
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    /**
     * Draws the components of the panel, with animations.
     * @param g The graphics object used to draw the components
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }
    
    //Constructor (Testing):
    View() {
        //Default settings. Using cardLayout to be able to switch between different screens throughout the game
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(cardLayout);
        this.add(homeScreen, "homeScreen");
        this.add(characterSelectionScreen, "characterSelectionScreen");
        cardLayout.show(this, "homeScreen");

        //Add action listeners to necessary JComponents:
        homeScreen.hostButton.addActionListener(this);
        homeScreen.joinButton.addActionListener(this);
        homeScreen.helpButton.addActionListener(this);

        //Default settings
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    //Testing purposes:
    public static void main(String[] args) {
        new View();
    }

}
