package view;
/*
 * THE VIEW CLASS CONTAINS CODE FOR FRONTEND PROGRAM -- ANIMATIONS AND SOME GAME LOGIC (i.e. hitboxes)
 */


import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameState;

public class MainView implements ActionListener, KeyListener {
    //Properties
    JFrame frame = new JFrame("View Testing");
    public static CardLayout cardLayout = new CardLayout();
    GameState state;
    public static JPanel panel = new JPanel();
    HomeView homeView;
    CharacterSelectionView characterSelectionView;

    Timer timer = new Timer(1000/60, this);

    //Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == timer) {
            panel.repaint();
        }

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

    // @Override
    // /**
    //  * Draws the components of the panel, with animations.
    //  * @param g The graphics object used to draw the components
    //  */
    // public void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     Graphics2D g2d = (Graphics2D) g;
    // }

    //Constructor (Testing):
    public MainView(GameState state) {
        this.state = state;
        homeView = new HomeView(this.state);
        characterSelectionView = new CharacterSelectionView(this.state);
        //Default settings. Using cardLayout to be able to switch between different screens throughout the game
        panel.setPreferredSize(new Dimension(1280, 720));
        panel.setLayout(cardLayout);
        panel.add(homeView, "homeScreen");
        panel.add(characterSelectionView, "characterSelectionView");
        cardLayout.show(panel, "homeScreen");

        //Default settings
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        timer.start();
    }
}
