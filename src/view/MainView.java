package view;
/*
 * THE VIEW CLASS CONTAINS CODE FOR FRONTEND PROGRAM -- ANIMATIONS AND SOME GAME LOGIC (i.e. hitboxes)
 */


import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameState;

public class MainView {
    //Properties
    public static JFrame frame = new JFrame("Mortal Kombat");
    public static CardLayout cardLayout = new CardLayout();
    GameState state;
    public static JPanel panel = new JPanel();
    HomeView homeView;
    CharacterSelectionView characterSelectionView;
    GameView gameView;
    HelpView helpView;

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
        // panel.add(helpView, "helpView");
        //panel.add(gameView, "gameView");
        cardLayout.show(panel, "homeScreen");

        //Default settings
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
