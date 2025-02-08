package kazune_shizumi.obj_to_mcfunction.math;

public class Vector3 {
    public final double x;
    public final double y;
    public final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 vector) {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3 subtract(Vector3 vector) {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
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
