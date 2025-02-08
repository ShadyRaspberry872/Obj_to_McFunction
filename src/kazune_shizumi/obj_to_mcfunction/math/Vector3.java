package kazune_shizumi.obj_to_mcfunction.math;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double[] c) {
        this(c[0], c[1], c[2]);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double[] toArray() {
        return new double[] { x, y, z };
    }

    public Vector3 addSelf(Vector3 vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        return this;
    }

    public Vector3 add(Vector3 vector) {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3 subtract(Vector3 vector) {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3 multiplySelf(Vector3 vector) {
        this.x *= vector.x;
        this.y *= vector.y;
        this.z *= vector.z;
        return this;
    }

    public Vector3 multiply(Vector3 vector) {
        return new Vector3(x * vector.x, y * vector.y, z * vector.z);
    }

    public Vector3 divide(Vector3 vector) {
        return new Vector3(x / vector.x, y / vector.y, z / vector.z);
    }

    public Vector3 interpolate(Vector3 vector, double t) {
        return new Vector3(x + t * (vector.x - x), y + t * (vector.y - y), z + t * (vector.z - z));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
