public class Main {
    public static void main(String[] args) {
        // Try to load saved game, if not found start new game
        GameI gameController =new GameController();
        gameController = gameController.load();
        gameController.startGame();
        // Save game progress when exiting
        gameController.write();
    }
}
 