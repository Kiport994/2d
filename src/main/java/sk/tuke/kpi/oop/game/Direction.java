package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1),
    NORTHEAST(1, 1),
    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getAngle() {
        switch (this) {
            case EAST:
                return 270f;
            case SOUTH:
                return 180f;
            case WEST:
                return 90f;
            case NORTH:
                return 0f;
            case SOUTHEAST:
                return 225f;
            case NORTHEAST:
                return 315f;
            case SOUTHWEST:
                return 135f;
            case NORTHWEST:
                return 45f;
                default:
                    return 0f;
        }
    }

    public Direction combine(Direction other) {
        if (this == other) {
            return this;
        }

        int togetherX = this.dx + other.dx;
        int togetherY = this.dy + other.dy;

        if(Math.abs(togetherX) == 2) {
            togetherX /= 2;
        }

        if(Math.abs(togetherY) == 2) {
            togetherY /= 2;
        }

        for (Direction direction : Direction.values()) {
            if (togetherX == direction.dx && togetherY == direction.dy) {
                return direction;
            }
        }

        return Direction.NONE;
    }

    public static Direction fromAngle(float angle) {
        float normalized = normalizeAngle(angle);
        return findClosestDirection(normalized);
    }

    private static float normalizeAngle(float angle) {
        float normal = angle % 360;
        if (normal < 0) {
            normal += 360;
        }
        return normal;
    }

    private static Direction findClosestDirection(float angle) {
        Direction closest = NONE;
        float minDifference = 360;
        for (Direction d : Direction.values()) {
            float diff = angleDifference(d.getAngle(), angle);
            if (diff < minDifference) {
                minDifference = diff;
                closest = d;
            }
        }
        return closest;
    }

    private static float angleDifference(float angle1, float angle2) {
        float diff = Math.abs(angle1 - angle2);
        if (diff > 180) {
            diff = 360 - diff;
        }
        return diff;
    }
}
