package controller;
import model.GameState;
import model.Player;

/**ChatController controls the chat logic in the game*/
public class ChatController {
    // Properties
    private GameState state;
    private Player player;

    // Methods
    /**
     * sendMessage function sends chat data to SSM to display to the chat screen
     * @param message Message to be sent
     */
    public void sendMessage(String message) {
        state.ssm.sendText("chat,"+player.name+","+message);
        state.chat.append(player.name + ": " + message + "\n");
    }

    /**
     * Constructor to initalize the chat controller
     * @param state Create GameState for SSM functionality here
     */
    public ChatController(GameState state) {
        this.state = state;
        this.player = state.currentPlayer;
    }
}
