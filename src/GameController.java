import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

public class GameController implements GameI{
    private GameBoard gameBoard;
    private int playerBudget;
    private int level;
    private int LaserTowerQuantity;
    private int BasicTowerQuantity;
    private int currentRound;

    public GameController() {
        this.level = 1;
        this.currentRound = 1;
        this.playerBudget = 100;
        this.gameBoard = new GameBoard(this);
    }

    public GameController(int level, int LaserTowerQuantity, int BasicTowerQuantity) {
        this.level = level;
        this.currentRound = 1;
        this.playerBudget = 100;
        this.LaserTowerQuantity = LaserTowerQuantity;
        this.BasicTowerQuantity = BasicTowerQuantity;
        this.gameBoard = new GameBoard(this);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    } 

    public int getPlayerBudget() {
        return playerBudget;
    }

    public void setPlayerBudget(int playerBudget) {
        this.playerBudget = playerBudget;
    }
    
    @Override
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Game Started! Level " + level + ", Round " + currentRound);
        System.out.println("You have $" + (double) playerBudget);
        spawnEnemies();

        while (true) {
            System.out.println("\nLevel: " + level + ", Round: " + currentRound + "/" + ROUNDS_PER_LEVEL);
            System.out.println("Budget: $" + playerBudget);
            System.out.println("1. Add Basic Tower ($20)");
            System.out.println("2. Add Laser Tower ($30)");
            System.out.println("3. Next Round");
            System.out.println("4. Save and Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                if (playerBudget >= 20) {
                    gameBoard.add(new BasicTower());
                    playerBudget -= 20;
                    System.out.println("Basic Tower placed! Remaining budget: $" + playerBudget);
                } else {
                    System.out.println("Not enough money!");
                }
            } else if (choice == 2) {
                if (playerBudget >= 30) {
                    gameBoard.add(new LaserTower());
                    playerBudget -= 30;
                    System.out.println("Laser Tower placed! Remaining budget: $" + playerBudget);
                } else {
                    System.out.println("Not enough money!");
                }
            } else if (choice == 3) {
                gameBoard.update();
                gameBoard.display();
                
                // First check if all enemies are defeated
                if (gameBoard.read()) {
                    currentRound++;
                    System.out.println("Round " + (currentRound - 1) + " completed!");
                    
                    // Check if level is complete
                    if (currentRound > ROUNDS_PER_LEVEL) {
                        level++;
                        currentRound = 1;
                        System.out.println("Level " + (level - 1) + " completed! Moving to level " + level);
                        // Increase difficulty for next level
                        playerBudget += 50; // Bonus money for completing level
                    }
                    
                    // Spawn new enemies for next round
                    spawnEnemies();
                } 
                // Then check if any enemy reached the end
                else if (gameBoard.hasEnemyReachedEnd()) {
                    System.out.println("Game Over! An enemy reached the end!");
                    break;
                }
            } else if (choice == 4) {
                write();
                System.out.println("Game saved! Final stats - Level: " + level + ", Round: " + currentRound + ", Budget: $" + playerBudget);
                break;
            }
        }
    }

    private Enemy spawnEnemies() {
        if (currentRound == ROUNDS_PER_LEVEL) {
            System.out.println("WARNING: BOSS APPROACHING!");
            Enemy boss=new Boss(50 * level, level);
            gameBoard.add(boss);  // Coercion from Boss to Enemy
            return boss;
            
        } else {
            Enemy enemy=new Boss(50 * level, level);
            gameBoard.add(enemy);
            
            return enemy;
        }
    }

    public void addMoney(int amount) {
        playerBudget += amount;
        System.out.println("You earned $" + amount + "! New budget: $" + playerBudget);
    }
    @Override
    public void write() {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write("level,laserTowerQuantity,basicTowerQuantity\n");
            writer.write(String.format("%d,%d,%d\n", level, LaserTowerQuantity, BasicTowerQuantity));
            System.out.println("Game progress saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving game progress: " + e.getMessage());
        }
    }
    @Override
    public GameController load() {
        // Create data directory if it doesn't exist
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            // Skip header line
            reader.readLine();
            
            String line = reader.readLine();
            if (line != null) {
                String[] stats = line.split(",");
                // Trim whitespace from each value before parsing
                int level = Integer.parseInt(stats[0].trim());
                int laserTowerQuantity = Integer.parseInt(stats[1].trim());
                int basicTowerQuantity = Integer.parseInt(stats[2].trim());
                
                return new GameController(level, laserTowerQuantity, basicTowerQuantity);
            }
        } catch (IOException e) {
            System.out.println("No saved game found or error loading game: " + e.getMessage());
        }
        // Return new game if no save file exists
        return new GameController(1, 1, 1);
    }}
