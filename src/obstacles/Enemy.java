package obstacles;
public class Enemy {
    private int health;
    private int speed;
    private int distance;

    public Enemy(int health, int speed) {
        this.health = health;
        this.speed = speed;
        this.distance = 13; // Start position
    }

    public void move() {
        if (health > 0) {
            distance -= speed;
            System.out.println("Enemy moves closer! Distance: " + distance);
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Prevent negative HP
        }
        System.out.println("Enemy took " + damage + " damage! HP left: " + health);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
