package view;
/*
 * THE VIEW CLASS CONTAINS CODE FOR FRONTEND PROGRAM -- ANIMATIONS AND SOME GAME LOGIC (i.e. hitboxes)
 */


import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameState;

/**
 * MainView is the global view that all views reference to whenever new JPanels need to be shown
 */
public class MainView {
    //Properties
    /**
     * static frame that all View classes use to be able to set JPanels inside the same frame
     */
    public static JFrame frame = new JFrame("Mortal Kombat");

    /**
     * static cardLayout that several View classes use to switch the current View/JPanel
     */
    public static CardLayout cardLayout = new CardLayout();

    /**
     * static MainView.panel that classes use to reference and replace the panel defined in the MainView
     */
    public static JPanel panel = new JPanel();

    private GameState state;
    private HomeView homeView;
    private CharacterSelectionView characterSelectionView;
    /**
     * Constructor to initalize the MainView
     */
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
    }
}
