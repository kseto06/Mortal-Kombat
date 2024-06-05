package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.GameState;

public class GameView extends JPanel implements ActionListener {
    //Properties
    GameState state;
    JLabel player1NameLabel = new JLabel(), player2NameLabel = new JLabel();
    JLabel player1HPCount = new JLabel(), player2HPCount = new JLabel();
    private final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

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
        g.drawImage(imgBackground, 0, 0, null);

        // g2d.setColor(Color.BLACK);
        // g2d.setStroke(new BasicStroke(4.0f));
        // g2d.drawLine(0, 680, 1280, 680); //The ground

        //Health bars and Player name labels
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2.5f));
        g2d.drawRect(20, 10, 500, 50); //each point of health = 0.5px
        g2d.drawRect(760, 10, 500, 50);
        g2d.fillRect(20, 10, (int)(state.player1.fighter.HP * 0.5), 50);
        g2d.fillRect(760, 10, (int)(state.player2.fighter.HP * 0.5), 50);
        player1NameLabel.setText(state.player1.name);
        player2NameLabel.setText(state.player2.name);
        player1HPCount.setText(String.valueOf(state.player1.fighter.HP) + "/1000");
        player2HPCount.setText(String.valueOf(state.player2.fighter.HP) + "/1000");


        //Animation updates and settings
        //Images stored in the fighter subclasses
        if (!state.player1.isAttacking && (state.player1.currentX <= state.player2.currentX)) {
            state.player1.currentAnimationImg = state.player1.fighter.idleLeft;
        } else if (!state.player1.isAttacking && (state.player1.currentX > state.player2.currentX)) {
            state.player1.currentAnimationImg = state.player1.fighter.idleRight;
        }
        
        if (!state.player2.isAttacking && (state.player1.currentX <= state.player2.currentX)) {
            state.player2.currentAnimationImg = state.player2.fighter.idleRight;
        } else if (!state.player2.isAttacking && (state.player1.currentX > state.player2.currentX)) {
            state.player2.currentAnimationImg = state.player2.fighter.idleLeft;
        }

        //Punching, update for other player to see
        if (state.currentPlayer.equals(state.player2) && state.player1.isAttacking && state.player1.currentAction.equals("punch")) {
            if (state.player1.currentX <= state.player2.currentX) {
                state.player1.currentAnimationImg = state.player1.fighter.punchLeft;
            } else if (state.player1.currentX > state.player2.currentX) {
                state.player1.currentAnimationImg = state.player1.fighter.punchRight;
            }
        }

        if (state.currentPlayer.equals(state.player1) && state.player2.isAttacking && state.player2.currentAction.equals("punch")) {
            if (state.player1.currentX <= state.player2.currentX) {
                state.player2.currentAnimationImg = state.player2.fighter.punchRight;
            } else if (state.player1.currentX > state.player2.currentX) {
                state.player2.currentAnimationImg = state.player2.fighter.punchLeft;
            }
        }

        //Kicking
        if (state.currentPlayer.equals(state.player2) && state.player1.isAttacking && state.player1.currentAction.equals("kick")) {
            if (state.player1.currentX <= state.player2.currentX) {
                state.player1.currentAnimationImg = state.player1.fighter.kickLeft;
            } else if (state.player1.currentX > state.player2.currentX) {
                state.player1.currentAnimationImg = state.player1.fighter.kickRight;
            }
        }

        if (state.currentPlayer.equals(state.player1) && state.player2.isAttacking && state.player2.currentAction.equals("kick")) {
            if (state.player1.currentX <= state.player2.currentX) {
                state.player2.currentAnimationImg = state.player2.fighter.kickRight;
            } else if (state.player1.currentX > state.player2.currentX) {
                state.player2.currentAnimationImg = state.player2.fighter.kickLeft;
            }
        }

        //Uppercuts
        if (state.currentPlayer.equals(state.player2) && state.player1.isAttacking && state.player1.currentAction.equals("uppercut")) {
            if (state.player1.currentX <= state.player2.currentX) {
                state.player1.currentAnimationImg = state.player1.fighter.uppercutLeft;
            } else if (state.player1.currentX > state.player2.currentX) {
                state.player1.currentAnimationImg = state.player1.fighter.uppercutRight;
            }
        }

        if (state.currentPlayer.equals(state.player1) && state.player2.isAttacking && state.player2.currentAction.equals("uppercut")) {
            if (state.player1.currentX <= state.player2.currentX) {
                state.player2.currentAnimationImg = state.player2.fighter.uppercutRight;
            } else if (state.player1.currentX > state.player2.currentX) {
                state.player2.currentAnimationImg = state.player2.fighter.uppercutLeft;
            }
        }

        //TODO: Special Moves Animations

        //Update movement (we'll see if this needs to be moved all the way to the top)
        g.drawImage(state.player1.currentAnimationImg, state.player1.currentX, state.player1.currentY, this); //Player 1 (Host)
        g.drawImage(state.player2.currentAnimationImg, state.player2.currentX, state.player2.currentY, this); // Client
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
        Timer jumpTimer = new Timer(500, new ActionListener() { //Dynamic timer puts a delay and then performs an action afterwards
            @Override
            public void actionPerformed(ActionEvent e) {
                state.currentPlayer.currentY += 180;
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",finished jump");
            }
        });
        jumpTimer.setRepeats(false);
        jumpTimer.start();
    }

    private void attackTimer() {
        Timer attackTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.currentPlayer.isAttacking = false;
                state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",finished attack");
            }
        });
        attackTimer.setRepeats(false);
        attackTimer.start();
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

        //JLabels
        player1NameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        player1NameLabel.setForeground(Color.BLACK);
        player1NameLabel.setSize(50, 30);
        player1NameLabel.setLocation(40, 20);
        player1NameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(player1NameLabel);

        player2NameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));
        player2NameLabel.setForeground(Color.BLACK);
        player2NameLabel.setSize(50, 30);
        player2NameLabel.setLocation(1150, 20);
        player2NameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(player2NameLabel);

        player1HPCount.setFont(new Font("Cambria", Font.PLAIN, 18));
        player1HPCount.setForeground(Color.GREEN);
        player1HPCount.setSize(100, 30);
        player1HPCount.setLocation(760 + 10, 21);
        player1HPCount.setVerticalAlignment(SwingConstants.CENTER);
        this.add(player1HPCount);

        player2HPCount.setFont(new Font("Cambria", Font.PLAIN, 18));
        player2HPCount.setForeground(Color.GREEN);
        player2HPCount.setSize(100, 30);
        player2HPCount.setLocation(420, 21);
        player2HPCount.setVerticalAlignment(SwingConstants.CENTER);
        this.add(player2HPCount);

        //Loading the fight background:
        //Try to read the image from both the jar file and local drive
        InputStream backgroundClass = this.getClass().getResourceAsStream("src/assets/FightBackground.jpg");
    
        if (backgroundClass != null) {
            try {
                imgBackground = ImageIO.read(backgroundClass);
            } catch (IOException e) {
                System.out.println("Unable to read/load image from jar");
                e.printStackTrace();
            }
        } else { //If it can't be found on the jar, search it locally
            try {
                imgBackground = ImageIO.read(new File("src/assets/FightBackground.jpg"));
            } catch (IOException e) {
                System.out.println("Unable to read/load image");
                e.printStackTrace();
            }
        }

        //Start the timer:
        timer.start();

        //Key Bindings for Movement
        //Key Pressed Equivalent:
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "right"); //Moving right
        this.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!state.currentPlayer.movementDisabled) {
                    state.currentPlayer.currentX += 10;
                    System.out.println((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",moved right");
                    state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",moved right");
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "left"); //Moving left
        this.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!state.currentPlayer.movementDisabled) {
                    state.currentPlayer.currentX -= 10;
                    System.out.println((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",moved left");
                    state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",moved left");
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "jump"); //Jumping
        this.getActionMap().put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                if (!state.currentPlayer.movementDisabled) {
                    state.currentPlayer.currentY -= 180;
                    state.ssm.sendText((state.currentPlayer.equals(state.player1) ? "host," : "client,") +state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",jump");
                    jumpTimer();
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("J"), "punch"); //Punching
        this.getActionMap().put("punch", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                if (state.currentPlayer.equals(state.player1) && !state.player1.movementDisabled) {
                    state.currentPlayer.punch(state.player2);
                    state.ssm.sendText("host,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",punch");
                    attackTimer();
                } else if (state.currentPlayer.equals(state.player2) && !state.player2.movementDisabled) {
                    state.currentPlayer.punch(state.player1);
                    state.ssm.sendText("client,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",punch");
                    attackTimer();
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("L"), "kick"); //Kicking
        this.getActionMap().put("kick", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                if (state.currentPlayer.equals(state.player1) && !state.player1.movementDisabled) {
                    state.currentPlayer.kick(state.player2);
                    state.ssm.sendText("host,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",kick");
                    attackTimer();
                } else if (state.currentPlayer.equals(state.player2) && !state.player2.movementDisabled) {
                    state.currentPlayer.kick(state.player1);
                    state.ssm.sendText("client,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",kick");
                    attackTimer();
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("K"), "uppercut"); //Uppercut
        this.getActionMap().put("uppercut", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                if (state.currentPlayer.equals(state.player1) && !state.player1.movementDisabled) {
                    state.currentPlayer.uppercut(state.player2);
                    state.ssm.sendText("host,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",uppercut");
                    attackTimer();
                } else if (state.currentPlayer.equals(state.player2) && !state.player2.movementDisabled) {
                    state.currentPlayer.uppercut(state.player1);
                    state.ssm.sendText("client,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",uppercut");
                    attackTimer();
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("I"), "special"); //Special Move
        this.getActionMap().put("special", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                 
                if (state.currentPlayer.equals(state.player1) && !state.player1.movementDisabled) {
                    state.currentPlayer.specialMove(state.player2);
                    state.ssm.sendText("host,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",special");
                    attackTimer();
                } else if (state.currentPlayer.equals(state.player2) && !state.player2.movementDisabled) {
                    state.currentPlayer.punch(state.player1);
                    state.ssm.sendText("client,"+state.currentPlayer.currentX+","+state.currentPlayer.currentY+","+state.currentPlayer.isAttacking+",special");
                    attackTimer();
                }
            }
        });

        
    }
}
