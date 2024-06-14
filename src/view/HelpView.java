package view;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import model.ScorpionFighter;
import model.SubzeroFighter;

/**
 * This class is the Help Screen, which will essentially be a Button Tutorial on how to play the fighting game
 * For example, how to punch, block, etc.
 */
public class HelpView extends JPanel implements ActionListener {
    //Properties
    private final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private ScorpionFighter helpScorpion;
    private int scorpX, scorpY;
    private SubzeroFighter helpSubzero;
    private int subX, subY;
    private BufferedImage currentScorpionImg;
    private BufferedImage currentSubzeroImg;
    private boolean[] tutorialFramePassed = new boolean[8];
    private boolean isDisplayed = false;
    private JLabel title = new JLabel();
    private JLabel instruction = new JLabel();
    private JTextArea description = new JTextArea();
    private JButton backButton = new JButton("Back");
    
    //Methods
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.drawLine(0, 680, 1280, 680); //The ground

        if (tutorialFramePassed[6] && !tutorialFramePassed[7]) {
            title.setText("Tutorial: Special Moves");
            instruction.setText("Press (I) to unleash your character's unique special move");
            description.setText("Sub-Zero: His Special Move will freeze the opponent" + 
            '\n' + 
            "Scorpion: His special move will launch a spear, when hit, will pull the opponent close to him" +
            '\n' +
            "Because both characters have different special moves, they will not be displayed here");
            description.setLineWrap(true); // Enable line wrap
            description.setWrapStyleWord(true);
            isDisplayed = true;
        }

        switch (checkFrameNumber(tutorialFramePassed)) {
            case 0:
                dFrame(g);
                break;
            case 1:
                aFrame(g);
                break;
            case 2:
                wFrame(g);
                break;
            case 3:
                jFrame(g);
                break;
            case 4:
                lFrame(g);
                break;
            case 5:
                kFrame(g);
                break;
            case 6: 
                uFrame(g);
                break;
            case 7:
                iFrame(g);
                break;
            default:
                title.setText("Training Passed");
                instruction.setText("Press Back to return to the home screen");
                break;
        }
        
        g.drawImage(currentScorpionImg, scorpX, scorpY, this); 
        g.drawImage(currentSubzeroImg, subX, subY, this);

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            MainView.cardLayout.show(MainView.panel, "homeScreen");
        }
    }

    private int checkFrameNumber(boolean[] arr) {
        int returnVal = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == false) {
                returnVal = i;
                break;
            }
        }

        return returnVal;
    }

    private void dFrame(Graphics g) {
        title.setText("Tutorial: Right Movement");
        instruction.setText("Press (D) to move right");
    }

    private void aFrame(Graphics g) {
        title.setText("Tutorial: Left Movement");
        instruction.setText("Press (A) to move Left");
    }

    private void wFrame(Graphics g) {
        title.setText("Tutorial: Jump");
        instruction.setText("Press (W) to jump");
        description.setText("Jumping may be helpful for dodging attacks, but you are still vulnerable to uppercuts");
        description.setVisible(true);
    }

    private void jFrame(Graphics g) {
        scorpX = 980 - 400;
        scorpY = 680 - helpScorpion.HEIGHT;
        subX = scorpX - 74;
        subY = 680 - helpSubzero.HEIGHT;

        title.setText("Tutorial: Punch");
        instruction.setText("Press (J) to punch");
        description.setText("Landing a punch will deal 50.0 damage");
        description.setVisible(true);
    }

    private void lFrame(Graphics g) {
        scorpX = 980 - 400;
        scorpY = 680 - helpScorpion.HEIGHT;
        subX = scorpX - 42;
        subY = 680 - helpSubzero.HEIGHT;

        title.setText("Tutorial: Kick");
        instruction.setText("Press (L) to kick");
        description.setText("Landing a kick will deal 80.0 damage");
        description.setVisible(true);
    }

    private void kFrame(Graphics g) {
        scorpX = 980 - 400;
        scorpY = 680 - helpScorpion.HEIGHT;
        subX = scorpX - 42;
        subY = 680 - helpSubzero.HEIGHT;

        title.setText("Tutorial: Uppercut");
        instruction.setText("Press (K) to uppercut");
        description.setText("Landing a uppercut will deal 140.0 damage");
        description.setVisible(true);
    }    

    private void uFrame(Graphics g) {
        scorpX = 980 - 400;
        scorpY = 680 - helpScorpion.HEIGHT;
        subX = scorpX - 52;
        subY = 680 - helpSubzero.HEIGHT;

        title.setText("Tutorial: Blocking");
        instruction.setText("Press (U) to block");
        description.setText("Blocking will reduce damage taken");
    }

    private void iFrame(Graphics g) {
        if (isDisplayed) {
            Timer sleep = new Timer(5000, e -> {
                tutorialFramePassed[7] = true;
                isDisplayed = false;
                repaint(); // Trigger a repaint after the delay
            });
            sleep.setRepeats(false);
            sleep.start();
            isDisplayed = false; // Ensures the timer is only set up once
        }
    }

    private void jumpTimer() {
        Timer jumpTimer = new Timer(500, new ActionListener() { //Dynamic timer puts a delay and then performs an action afterwards
            @Override
            public void actionPerformed(ActionEvent e) {
                subY += 180;
                tutorialFramePassed[2] = true;
            }
        });
        jumpTimer.setRepeats(false);
        jumpTimer.start();
    }

    private void attackTimer(String attack, int index) {
        int delay = 0; //Default
        if (attack.equals("punch")) {
            delay = 250;
        } else if (attack.equals("kick")) {
            delay = 500;
        } else if (attack.equals("uppercut")) {
            delay = 1000;
        } 

        Timer attackTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attack.equals("uppercut")) {
                    scorpY += 180; //reset ground position
                }

                currentScorpionImg = helpScorpion.idleRight;
                currentSubzeroImg = helpSubzero.idleLeft;
                tutorialFramePassed[index] = true; //3,4,5,6
            }
        });
        attackTimer.setRepeats(false);
        attackTimer.start();
    }
    
    /**
     * Constructor to intialize the HelpView
     */
    public HelpView() {
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        //New fighter instances 
        helpScorpion = new ScorpionFighter();
        helpSubzero = new SubzeroFighter();

        try {
            currentSubzeroImg = helpSubzero.idleLeft;
            currentScorpionImg = helpScorpion.idleRight;

            subX = 400 - helpSubzero.WIDTH;
            subY = 680 - helpSubzero.HEIGHT;
            scorpX = 1280 - 400;
            scorpY = 680 - helpScorpion.HEIGHT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //JLabel formatting
        title.setFont(new Font("Cambria", Font.BOLD, 30));
        title.setForeground(Color.BLACK);
        title.setSize(700, 50);
        title.setLocation(290, 10);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);

        instruction.setFont(new Font("Cambria", Font.BOLD, 25));
        instruction.setForeground(Color.BLACK);
        instruction.setSize(1280, 50);
        instruction.setLocation(0, 65);
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(instruction);

        description.setFont(new Font("Cambria", Font.PLAIN, 15));
        description.setForeground(Color.BLACK);
        description.setSize(1260, 100);
        description.setLocation(10, 120);
        description.setEditable(false);
        description.setVisible(false);
        this.add(description);

        //JButton:
        backButton.setFont(new Font("Cambria", Font.BOLD, 30));
        backButton.setForeground(Color.BLACK);
        backButton.setSize(200, 50);
        backButton.setLocation(0, 0);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(this);
        this.add(backButton);

        //Key listeners:
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "right"); //Moving right
        this.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 0) {
                    subX += 10;
                    instruction.setText("Passed");
                    tutorialFramePassed[0] = true;
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "left"); //Moving right
        this.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 1) {
                    subX -= 10;
                    instruction.setText("Passed");
                    tutorialFramePassed[1] = true;
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "jump"); //Moving right
        this.getActionMap().put("jump", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 2) {
                    subY -= 180;
                    jumpTimer();
                    instruction.setText("Passed");
                    
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("J"), "punch"); //Moving right
        this.getActionMap().put("punch", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 3) {
                    currentSubzeroImg = helpSubzero.punchLeft;
                    currentScorpionImg = helpScorpion.staggerRight;
                    scorpX += 40;
                    attackTimer("punch", 3);
                    instruction.setText("Passed");

                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("L"), "kick"); //Moving right
        this.getActionMap().put("kick", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 4) {
                    currentSubzeroImg = helpSubzero.kickLeft;
                    currentScorpionImg = helpScorpion.staggerRight;
                    scorpX += 80;
                    attackTimer("kick", 4);
                    instruction.setText("Passed");
                    //tutorialFramePassed[4] = true;
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("K"), "uppercut"); //Moving right
        this.getActionMap().put("uppercut", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 5) {
                    currentSubzeroImg = helpSubzero.uppercutLeft;
                    currentScorpionImg = helpScorpion.staggerRight;
                    scorpX += 30;
                    scorpY -= 180;
                    attackTimer("uppercut", 5);
                    instruction.setText("Passed");
                    // tutorialFramePassed[5] = true;
                }
            }
        });

        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("U"), "block"); //Moving right
        this.getActionMap().put("block", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrameNumber(tutorialFramePassed) == 6) {
                    currentSubzeroImg = helpSubzero.blockLeft;
                    currentScorpionImg = helpScorpion.kickRight;
                    attackTimer("kick", 6);
                    instruction.setText("Passed");
                    // tutorialFramePassed[6] = true;
                }
            }
        });

        title.setText("Welcome to the Tutorial");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HelpView();
    }
}
