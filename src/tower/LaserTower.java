package tower;
import java.util.ArrayList;

import obstacles.Enemy;

public class LaserTower extends Tower {
    public LaserTower() {
        super(10, 6); // Damage: 5, Range: 5
    }

    @Override
    public void attack(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.getDistance() <= range) {
                enemy.takeDamage(damage);
                System.out.println("Laser Tower hits multiple enemies!");
System.out.println("Enemy took " + damage + " damage! HP left: " + enemy.getHealth());
            }
        }
    }
}
