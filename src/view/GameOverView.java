package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * GameOverView is a JPanel that displays the game over screen when the game ends.
 */
public class GameOverView extends JPanel {
    //Properties
    GameState state;
    private JLabel title = new JLabel("Game Over!");
    private JLabel winner = new JLabel();
    
    /**
     * Constructor to initialize the GameOverView
     * @param state GameState determines if game is over or not
     */
    public GameOverView(GameState state) {
        super();
        this.state = state;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        //JLabel formatting
        title.setFont(new Font("Cambria", Font.BOLD, 30));
        title.setForeground(Color.BLACK);
        title.setSize(700, 50);
        title.setLocation(290, 10);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);

        if (state.player1.fighter.HP <= 0) {
            winner.setText(state.player2.name+" Wins!");
        } else {
            winner.setText(state.player1.name + " Wins!");
        }
        winner.setFont(new Font("Cambria", Font.BOLD, 30));
        winner.setForeground(Color.BLACK);
        winner.setSize(700, 50);
        winner.setLocation(290, 50);
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(winner);
    }
}
