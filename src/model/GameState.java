package model;

import java.awt.event.ActionListener;

import network.SuperSocketMaster;
import view.ChatView;

public class GameState {
    // Properties
    /** 
     * GameState player 1 initialization
     */
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public boolean isGameOver = false; 
    public String ipAddress;
    public SuperSocketMaster ssm;
    public ActionListener listener;
    public IceBall iceBall1, iceBall2;
    public Spear spear1, spear2;
    public StringBuilder chat = new StringBuilder();
    public ChatView chatView;
}
