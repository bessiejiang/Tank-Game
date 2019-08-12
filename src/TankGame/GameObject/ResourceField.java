package TankGame.GameObject;

public enum ResourceField {
    BACKGROUND(0),
    WALL(1),
    BREAKABLE_WALL(2),
    TANK1(3),
    TANK2(4),
    BULLET(5);

    private final int val;

    ResourceField(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
