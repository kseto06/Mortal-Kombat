package model;

import java.awt.event.ActionListener;

import network.SuperSocketMaster;

public class GameState {
    // Properties
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public boolean isGameOver = false; 
    public String ipAddress;
    public SuperSocketMaster ssm;
    public ActionListener listener;
}
