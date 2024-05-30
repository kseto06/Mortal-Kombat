package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

public class Listener implements ActionListener {
    GameState state;
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == state.ssm) {
            String msg = state.ssm.readText();
            System.out.println("Reading message: "+msg);
            String[] msgComponents = msg.split(",");
            System.out.println("Component[0]: "+msgComponents[0] + " Component[1]:"+msgComponents[1] + " Component[2]:"+msgComponents[2]);

            if (msgComponents[0].equals("Host")) {
                state.player1 = new Player(msgComponents[1]); //Update other screen
                state.player1.chooseFighter(msgComponents[2]);
                System.out.println(state.player1.fighter.name);
            } else if (msgComponents[0].equals("Client")) {
                state.player2 = new Player(msgComponents[1]);
                state.player2.chooseFighter(msgComponents[2]);
                System.out.println(state.player2.fighter.name);
            }
        }
    }

    public Listener(GameState state) {
        this.state = state;
    }
}
