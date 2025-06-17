package logic;
import java.util.ArrayList;
import java.util.List;

import obstacles.Boss;
import obstacles.Enemy;
import tower.Tower;

public class GameBoard implements GameBoardI{
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private GameController gameController;
    private static final int END_DISTANCE = 0;

    public GameBoard(GameController gameController) {
        this.towers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.gameController = gameController;
    }

    public void add(Tower tower) {
        towers.add(tower);
    }

    public void add(Enemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public void update() {
        // First, let towers attack
        for (Tower tower : towers) {
            tower.attack(enemies);
        }

        // Then handle enemy movement and special abilities
        for (Enemy enemy : enemies) {
            enemy.move();
            
            // Check if enemy is a Boss and handle special ability
            if (enemy instanceof Boss) {
                Boss boss = (Boss) enemy;
                if (boss.getSpecialAbilityCooldown() <= 0) {
                    handleBossSpecialAbility(boss);
                }
            }
        }

        // Remove defeated enemies
        delete();
    }

    private void handleBossSpecialAbility(Boss boss) {
        // Boss deals damage to all towers in range
        for (Tower tower : towers) {
            if (tower.getDistance() <= boss.getRange()) {
                tower.takeDamage(boss.getSpecialAbilityDamage());
                System.out.println("Boss special ability hit a tower!");
            }
        }
    }

    @Override
    public void delete() {
        ArrayList<Enemy> defeated = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() <= 0) {
                System.out.println("Enemy defeated! You earned $20.");
                defeated.add(enemy);
                gameController.addMoney(20);  // Reward the player
            }
        }
        enemies.removeAll(defeated);
    }

    public boolean hasEnemyReachedEnd() {
        for (Enemy enemy : enemies) {
            if (enemy.getDistance() <= END_DISTANCE && enemy.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean read() {
        return enemies.isEmpty();
    }

    public void display() {
        System.out.println("Towers: " + towers.size() + ", Enemies: " + enemies.size());
    }

    @Override
    public void create() {
        List<Tower> tower= new ArrayList<>();
    }
}
