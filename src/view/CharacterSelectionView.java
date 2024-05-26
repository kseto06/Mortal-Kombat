package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CharacterSelectionView extends JPanel implements ActionListener {
    //Properties
    public JButton startGameButton1 = new JButton("Start Game");
    public JButton startGameButton2 = new JButton("Start Game"); 
    public boolean player1Ready = false;
    public boolean player2Ready = false;

    //Methods

    //Paint images of the characters:

    @Override
    public void actionPerformed(ActionEvent evt) {
        //Change button functions to be ready/return based on what the user selects
        if (evt.getSource() == startGameButton1 && startGameButton1.getText().equals("Start Game")) {
            player1Ready = true;
            startGameButton1.setText("Return");
        } else if (evt.getSource() == startGameButton1 && startGameButton1.getText().equals("Return")) {
            player1Ready = false;
            startGameButton1.setText("Start Game");
        }

        if (evt.getSource() == startGameButton2 && startGameButton2.getText().equals("Start Game")) {
            player2Ready = true;
            startGameButton2.setText("Return");
        } else if (evt.getSource() == startGameButton2 && startGameButton2.getText().equals("Return")) {
            player2Ready = false;
            startGameButton2.setText("Start Game");
        }
    }

    //Constructor
}
