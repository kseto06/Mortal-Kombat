package model;

import java.awt.event.ActionListener;

import network.SuperSocketMaster;
import view.ChatView;

/**GameState class to keep track of the state of the game*/
public class GameState {
    // Properties

    /**GameState player 1 initialization*/
    public Player player1;

    /**GameState player 2 initialization*/
    public Player player2;

    /**GameState currentPlayer initialization*/
    public Player currentPlayer;

    /**Checks if game is over*/
    public boolean isGameOver = false; 

    /**ipAddress of the GameState, initialized in HomeView when host starts a game*/
    public String ipAddress;

    /**Global SuperSocketMaster*/
    public SuperSocketMaster ssm;

    /**Global Listener for SSM*/
    public ActionListener listener;

    /**Global IceBall objects for Player 1 and 2*/
    public IceBall iceBall1, iceBall2;

    /**Global Spear objects for Player 1 and 2*/
    public Spear spear1, spear2;

    /**Chat Input*/
    public StringBuilder chat = new StringBuilder();

    /**Chat View, can be opened mid-game*/
    public ChatView chatView;
}
