import controller.Listener;
import model.GameState;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        GameState state = new GameState();
        Listener listener = new Listener(state);
        state.listener = listener;
        new MainView(state);
    }
}
