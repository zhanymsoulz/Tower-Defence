public interface GameI {
    String SAVE_FILE = "./data/game_stats.csv";
    int ROUNDS_PER_LEVEL = 5;
    void write ();
    <T> T load();
    void startGame();
}
