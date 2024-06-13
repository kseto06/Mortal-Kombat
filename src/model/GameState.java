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
    public IceBall iceBall1, iceBall2;
    public Spear spear1, spear2;
}
