package tower;
import java.util.ArrayList;

import obstacles.Enemy;

public class BasicTower extends Tower {
    public BasicTower() {
        super(15, 4); // Damage: 10, Range: 3
    }

    @Override
    public void attack(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.getDistance() <= range) {
                enemy.takeDamage(damage);
                System.out.println("Basic Tower hits multiple enemies!");
System.out.println("Enemy took " + damage + " damage! HP left: " + enemy.getHealth());

                break; // Attack only one enemy per turn
            }
        }
    }
}
