package kazune_shizumi.obj_to_mcfunction.math;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double[] c) {
        this(c[0], c[1]);
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] toArray() {
        return new double[] { x, y };
    }

    public Vector2 addSelf(Vector2 vector) {
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Vector2 add(Vector2 vector) {
        return new Vector2(x + vector.x, y + vector.y);
    }

    public Vector2 subtract(Vector2 vector) {
        return new Vector2(x - vector.x, y - vector.y);
    }

    public Vector2 multiplySelf(Vector2 vector) {
        this.x *= vector.x;
        this.y *= vector.y;
        return this;
    }

    public Vector2 multiply(Vector2 vector) {
        return new Vector2(x * vector.x, y * vector.y);
    }

    public Vector2 divide(Vector2 vector) {
        return new Vector2(x - vector.x, y - vector.y);
    }

    public Vector2 interpolate(Vector2 vector, double t) {
        return new Vector2(x + t * (vector.x - x), y + t * (vector.y - y));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
