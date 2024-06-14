package controller;
import model.GameState;
import model.Player;

public class ChatController {
    // Properties
    private GameState state;
    private Player player;

    // Methods
    public void sendMessage(String message) {
        state.ssm.sendText("chat,"+player.name+","+message);
        state.chat.append(player.name + ": " + message + "\n");
    }

    // Constructor
    public ChatController(GameState state) {
        this.state = state;
        this.player = state.currentPlayer;
    }
}
