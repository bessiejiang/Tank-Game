package TankGame.GameObject;

public enum ResourceField {
    // Sprites
    BACKGROUND(0),
    UNBREAKABLEWALL(1),
    BREAKABLE_WALL(2),
    TANK1(3),
    TANK2(4),
    BULLET(5),
    POWERUP(7),
    //LifeIcon(8),

    // Sounds
    MUSIC(6);

    private final int val;

    ResourceField(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
