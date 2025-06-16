import java.util.ArrayList;

public class BasicTower extends Tower {
    public BasicTower() {
        super(10, 3); // Damage: 10, Range: 3
    }

    @Override
    public void attack(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.getDistance() <= range) {
                enemy.takeDamage(damage);
                System.out.println("Basic Tower attacks! Enemy HP: " + enemy.getHealth());
                break; // Attack only one enemy per turn
            }
        }
    }
}
