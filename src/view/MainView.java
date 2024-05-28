package view;
/*
 * THE VIEW CLASS CONTAINS CODE FOR FRONTEND PROGRAM -- ANIMATIONS AND SOME GAME LOGIC (i.e. hitboxes)
 */


import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Player;
import network.*;

public class MainView extends JPanel implements ActionListener, KeyListener {
    //Properties
    JFrame frame = new JFrame("View Testing");
    CardLayout cardLayout = new CardLayout();
    HomeView homeView = new HomeView();
    CharacterSelectionView characterSelectionView = new CharacterSelectionView();

    //Server Properties:
    MulticastServer ms;
    MulticastClient mc;
    SuperSocketMaster ssm;

    String hostPlayerName, clientPlayerName;
    Timer timer = new Timer(1000/60, this);
    Set<String> serverSet = new HashSet<>();

    //Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (characterSelectionView.hostReady == true && characterSelectionView.clientReady == true) {
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
    public MainView() {
        //Default settings. Using cardLayout to be able to switch between different screens throughout the game
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(cardLayout);
        this.add(homeView, "homeScreen");
        this.add(characterSelectionView, "characterSelectionView");
        cardLayout.show(this, "homeScreen");

        //Default settings
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    //Testing purposes:
    public static void main(String[] args) {
        new MainView();
    }

}
