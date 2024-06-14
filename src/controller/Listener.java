package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

/**
 * Listener class listens to changes in the global GameState that relates to SSM
 */
public class Listener implements ActionListener {
    GameState state;
    
    /**
     * ActionPerformed here reads the socketed message text and assigns it to the GameState's variables
     * @param evt ActionEvent to track changes in SSM through readText
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == state.ssm) {
            String msg = state.ssm.readText();
            //System.out.println("Reading message: "+msg);
            String[] msgComponents = msg.split(",");

            //Character Selection View Socket
            if (msgComponents[0].equals("Host")) {
                state.player1 = new Player(msgComponents[1]); //Update other screen
                state.player1.chooseFighter(msgComponents[2]);
                System.out.println(state.player1.fighter.name);
            } else if (msgComponents[0].equals("Client")) {
                state.player2 = new Player(msgComponents[1]);
                state.player2.chooseFighter(msgComponents[2]);
                System.out.println(state.player2.fighter.name);
                // send host selection in case client joined after host selected
                if (state.player1 != null) {
                    state.ssm.sendText("Host," + state.player1.name + "," + state.player1.fighter.name);
                }
            }

            //Game View Socket
            //Message Format: userType, currentX, currentY
            else if (msgComponents[0].equals("host") && state.player1 != null) {
                try {
                    state.player1.currentX = Integer.valueOf(msgComponents[1]);
                    state.player1.currentY = Integer.valueOf(msgComponents[2]);

                    //Attacking
                    if (msgComponents[3] != null && !msgComponents[3].isEmpty()) {
                    state.player1.isAttacking = msgComponents[3].equals("true");
                    }
                    //Check the specific action
                    if (msgComponents[4] != null && !msgComponents[4].isEmpty()) {
                        state.player1.currentAction = msgComponents[4];
                    }
                    //Movement Disabled Check
                    if (msgComponents[5] != null && !msgComponents[5].isEmpty()) {
                        state.player1.movementDisabled = msgComponents[5].equals("true");
                    }
                    // HP Update
                    if (msgComponents[6] != null && !msgComponents[6].isEmpty()) {
                        state.player1.fighter.HP = Integer.valueOf(msgComponents[6]);
                    }
                    //Stagger Has Run Check
                    if (msgComponents[7] != null && !msgComponents[7].isEmpty()) {
                        state.player1.hasRun = msgComponents[7].equals("true");
                    }
                    //Special Move Check
                    if (msgComponents[8] != null && !msgComponents[8].isEmpty()) {
                        state.player1.fighter.isSpecialBeingUsed = msgComponents[8].equals("true");
                    }
                    //Block Check
                    if (msgComponents[9] != null && !msgComponents[9].isEmpty()) {
                        System.out.println("Block: " + msgComponents[9]);
                        if (msgComponents[9].equals("true")) {
                            state.player1.block(state.player2);
                        }
                    }
                //General Exception to catch both ArrayIndexOutOfBoundsException and NullPointerException
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            } else if (msgComponents[0].equals("client") && state.player2 != null) {
                try {
                    state.player2.currentX = Integer.valueOf(msgComponents[1]);
                    state.player2.currentY = Integer.valueOf(msgComponents[2]);

                    //Attacking
                    if (msgComponents[3] != null && !msgComponents[3].isEmpty()) {
                        state.player2.isAttacking = msgComponents[3].equals("true");
                    }
                    //Check the specific action
                    if (msgComponents[4] != null && !msgComponents[4].isEmpty()) {
                        state.player2.currentAction = msgComponents[4];
                    }         
                    //Movement Disabled Check
                    if (msgComponents[5] != null && !msgComponents[5].isEmpty()) {
                        state.player2.movementDisabled = msgComponents[5].equals("true");
                    }
                    // HP Update
                    if (msgComponents[6] != null && !msgComponents[6].isEmpty()) {
                        state.player2.fighter.HP = Integer.valueOf(msgComponents[6]);
                    }
                    //Stagger Has Run Check
                    if (msgComponents[7] != null && !msgComponents[7].isEmpty()) {
                        state.player2.hasRun = msgComponents[7].equals("true");
                    }
                    //Special Move Check
                    if (msgComponents[8] != null && !msgComponents[8].isEmpty()) {
                        state.player2.fighter.isSpecialBeingUsed = msgComponents[8].equals("true");
                    }
                    //Block Check
                    if (msgComponents[9] != null && !msgComponents[9].isEmpty()) {
                        System.out.println("Block: " + msgComponents[9]);
                        if(msgComponents[9].equals("true")) {
                            state.player2.block(state.player1);
                        }
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }       
                
            }

            //Special Move Sockets:
            if (msgComponents[0].equals("spear1") && state.spear1 != null && state.player1 != null) {
                try {
                    state.spear1.spearX = Integer.valueOf(msgComponents[1]);
                    state.spear1.toRender = msgComponents[2].equals("true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (msgComponents[0].equals("spear2") && state.spear2 != null && state.player2 != null) {
                try {
                    state.spear2.spearX = Integer.valueOf(msgComponents[1]);
                    state.spear2.toRender = msgComponents[2].equals("true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (msgComponents[0].equals("iceBall1") && state.iceBall1 != null && state.player1 != null) {
                try {
                    state.iceBall1.iceBallX = Integer.valueOf(msgComponents[1]);
                    state.iceBall1.toRender = msgComponents[2].equals("true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (msgComponents[0].equals("iceBall2") && state.iceBall2 != null && state.player2 != null) {
                try {
                    state.iceBall2.iceBallX = Integer.valueOf(msgComponents[1]);
                    state.iceBall2.toRender = msgComponents[2].equals("true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (msgComponents[0].equals("chat")) {
                String chatMsg = msg.split(",", 3)[2];
                state.chat.append(msgComponents[1] + ": " + chatMsg + "\n");
                state.chatView.updateChat();
            }
        }
    }

    /**
     * Constructor to initialize the Listener
     * @param state Tracks changes in the GameState
     */
    public Listener(GameState state) {
        this.state = state;
    }
}
