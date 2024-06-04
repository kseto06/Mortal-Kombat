package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.GameState;

public class GameView extends JPanel implements ActionListener {
    //Properties
    GameState state;
    //private boolean isJumpPressed = false; //Prevents players from infinitely spamming/holding down jump key
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

    //Init images
    /**
     * Fight Background
     */
    BufferedImage imgBackground;
    Timer timer = new Timer(1000/60, this);

    //Methods
      /**
     * GameView's paintComponent paints the actual UI on the screen
     * @param g Graphics used to paint
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawLine(0, 680, 1280, 680); //The ground
        

        //Idle / ONLY Moving animations
            //Images stored in the fighter subclasses\
            //TODO: draw images for punching or wtv -- give more specific actions lower priority (checked last) on condition checking        
        if (state.player1.currentX <= state.player2.currentX) {
            g.drawImage(state.player1.fighter.idleLeft, state.player1.currentX, state.player1.currentY, this); //Player 1
            g.drawImage(state.player2.fighter.idleRight, state.player2.currentX, state.player2.currentY, this);
        } else {
            g.drawImage(state.player1.fighter.idleRight, state.player1.currentX, state.player1.currentY, this);
            g.drawImage(state.player2.fighter.idleLeft, state.player2.currentX, state.player2.currentY, this);
        }

        if (state.player1.currentAnimationImg == state.player1.fighter.punchLeft) {
            g.drawImage(state.player1.fighter.punchLeft, state.player1.currentX, state.player1.currentY, this);
            //Pause program for a bit of time? Does this work? Ask Martin
        }

        if (state.player2.currentAnimationImg == state.player2.fighter.punchLeft) {
            g.drawImage(state.player2.fighter.punchLeft, state.player2.currentX, state.player2.currentY, this);
        }
        
        // if (state.player1.fighter.name.equals("Scorpion") && state.player1.isAttacking) {

        // }
        
        // if (state.player1.fighter.name.equals("Scorpion")) {
        //     if (state.player1.currentX <= state.player2.currentX) { //player1 is on the left
        //         g.drawImage(scorpionIdleLeft, state.player1.currentX, state.player1.currentY, this);
        //     } else {
        //         g.drawImage(scorpionIdleRight, state.player2.currentX, state.player2.currentY, this);
        //     }
            
        // } 
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == timer) {
            if (state.player1 == null || state.player2 == null) { return; }

            //Force animations to be within frame
            if (state.player1.currentX < 0) {
                state.player1.currentX = 0;
            } else if (state.player1.currentX > 1280 - state.player1.fighter.WIDTH) {
                state.player1.currentX = 1280 - state.player1.fighter.WIDTH;
            }

            if (state.player2.currentX < 0) {
                state.player2.currentX = 0;
            } else if (state.player2.currentX > 1280 - state.player2.fighter.WIDTH) {
                state.player2.currentX = 1280 - state.player2.fighter.WIDTH;
            }

            if (state.player1.currentY < 500 - state.player1.fighter.HEIGHT) { //Ensure jump doesn't overshoot
                state.player1.currentY = 500 - state.player1.fighter.HEIGHT; 
            } else if (state.player1.currentY > 680 - state.player1.fighter.HEIGHT) { //Ensure landing doesn't overshoot
                state.player1.currentY = 680 - state.player1.fighter.HEIGHT;
            }

            if (state.player2.currentY < 500 - state.player2.fighter.HEIGHT) { //Ensure jump doesn't overshoot
                state.player2.currentY = 500 - state.player2.fighter.HEIGHT; 
            } else if (state.player2.currentY > 680 - state.player2.fighter.HEIGHT) { //Ensure landing doesn't overshoot
                state.player2.currentY = 680 - state.player2.fighter.HEIGHT;
            }

            this.repaint();
        }
    }    

    //Animation functionalities:
    private void jumpTimer() {
        Timer jumpTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.currentPlayer.currentY += 180;
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
            }
        });
        jumpTimer.setRepeats(false);
        jumpTimer.start();
    }

    private void attackTimer(String attack) {
        Timer attackTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void animateScorpionSpear(Graphics g, int x, int y) {
        //TODO: animate scorpion's spear throw -- changes based on which side

    }

    private void animateIceBall(Graphics g) {
        //TODO: animate Sub Zero's ice ball -- freezes opponent for set amount of time
        //While ice ball is being thrown, do we want it to be stuck in that pose/image?
    }    

    //Constructor
    public GameView(GameState state) {
        super();
        this.state = state;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        //Start the timer:
        timer.start();

        //Key Bindings for Movement
        //Key Pressed Equivalent:
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "right"); //Moving right
        this.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.currentPlayer.currentX += 10;
                System.out.println((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "left"); //Moving left
        this.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.currentPlayer.currentX -= 10;
                System.out.println((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "jump"); //Jumping
        this.getActionMap().put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                state.currentPlayer.currentY -= 180;
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
                jumpTimer();
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "jump"); //Jumping
        this.getActionMap().put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                state.currentPlayer.currentY -= 180;
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY);
                jumpTimer();
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("J"), "punch"); //Jumping
        this.getActionMap().put("punch", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                state.currentPlayer.isAttacking = true;
                if (state.currentPlayer.equals(state.player1)) {
                    state.ssm.sendText("host,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY);
                } else {
                    state.ssm.sendText("client,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY);
                }
                
            }
        });

        
    }
}
