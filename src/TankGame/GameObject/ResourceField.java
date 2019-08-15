package TankGame.GameObject;

public enum ResourceField {
    // Sprites
    BACKGROUND(0),
    UNBREAKABLE_WALL(1),
    BREAKABLE_WALL(2),
    TANK1(3),
    TANK2(4),
    BULLET(5),
    BULLET2(6),
    LIFE_POWER_UP(7),
    BULLET_POWER_UP(8),
    LIFE_ICON1(9),
    LIFE_ICON2(10),
    MENU(11),
    WIN_OR_LOSE(12),

    // Sounds
    MUSIC(13);

    private final int val;

    ResourceField(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
