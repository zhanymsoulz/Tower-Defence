import logic.GameController;
import logic.GameI;

public class Main {
    public static void main(String[] args) {
        // Try to load saved game, if not found start new game
        GameI gameController =new GameController().load();
        gameController.startGame();
    }
}
 