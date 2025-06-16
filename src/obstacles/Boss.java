package obstacles;
public class Boss extends Enemy {
    private int specialAbilityCooldown;
    private static final int SPECIAL_ABILITY_DAMAGE = 30;
    private static final int SPECIAL_ABILITY_COOLDOWN = 3;
    private static final int BOSS_RANGE = 3;

    public Boss(int health, int speed) {
        super(health * 2, speed); // Boss has double health
        this.specialAbilityCooldown = 0;
    }

    @Override
    public void move() {
        super.move();
        // Boss has a chance to use special ability
        if (specialAbilityCooldown <= 0) {
            useSpecialAbility();
            specialAbilityCooldown = SPECIAL_ABILITY_COOLDOWN;
        } else {
            specialAbilityCooldown--;
        }
    }

    private void useSpecialAbility() {
        System.out.println("BOSS USES SPECIAL ABILITY: Area Damage!");
        // Boss deals damage to all towers in range
        // This will be handled by the GameBoard
    }

    public int getSpecialAbilityDamage() {
        return SPECIAL_ABILITY_DAMAGE;
    }

    public int getSpecialAbilityCooldown() {
        return specialAbilityCooldown;
    }

    public int getRange() {
        return BOSS_RANGE;
    }

    @Override
    public String toString() {
        return "BOSS - Health: " + getHealth() + ", Speed: " + getSpeed();
    }
}
