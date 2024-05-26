package controller;
import model.GameState;
import network.SuperSocketMaster;
import view.GameView;

public class GameController {
    // Properties
    private GameState state;
    private GameView view;
    private SuperSocketMaster ssm;

    // Methods
    public void startGameLoop() {
        // TO-DO: Implement startGameLoop method
    }

    // Constructor
    public GameController(GameState state, GameView view, SuperSocketMaster ssm) {
        this.state = state;
        this.view = view;
        this.ssm = ssm;
    }
}
