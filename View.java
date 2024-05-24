import Panels.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class View implements ActionListener {
    //Properties
    HomeScreen homeScreen = new HomeScreen();
    CharacterSelectionScreen characterSelectionScreen = new CharacterSelectionScreen();
    Model model = new Model();
    static String[] playerList = new String[2];


    //Methods
    @Override
    public void actionPerformed(ActionEvent evt) {
        //If the user presses the host button on the home screen, open SSM to host their game
        if (evt.getSource() == homeScreen.hostButton) {
            //One person has joined -- manipulate the CharacterSelectionScreen to have a "waiting for opponent"
            model.hostServer();

        } else if (evt.getSource() == homeScreen.joinButton) {
            //Two people now in-game, manipulate the CharacterSelectionScreen to show everything
            model.clientServer();
        
        } else if (characterSelectionScreen.player1Ready == true && characterSelectionScreen.player2Ready == true) {
            //Two people done selecting, start game

        } 
    }
    
    //Constructor
    View() {

    }
}
