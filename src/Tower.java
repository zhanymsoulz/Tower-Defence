import java.util.ArrayList;

public abstract class Tower {
    protected int damage;
    protected int range;
    protected int health;
    protected int distance;

    public Tower(int damage, int range) {
        this.damage = damage;
        this.range = range;
        this.health = 100;
        this.distance = 0;
    }

    public abstract void attack(ArrayList<Enemy> enemies);
    
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println("Tower took " + damage + " damage! Health: " + health);
    }

    public int getDistance() {
        return distance;
    }

    public int getHealth() {
        return health;
    }
}
