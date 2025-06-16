import java.util.ArrayList;

public class LaserTower extends Tower {
    public LaserTower() {
        super(5, 5); // Damage: 5, Range: 5
    }

    @Override
    public void attack(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.getDistance() <= range) {
                enemy.takeDamage(damage);
                System.out.println("Laser Tower hits multiple enemies! Enemy HP: " + enemy.getHealth());
            }
        }
    }
}
