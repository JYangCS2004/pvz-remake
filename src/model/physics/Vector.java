package model.physics;

public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector v) {
        x = x + v.x;
        y = y + v.y;
    }

    public void normalize() {
        double len = Math.sqrt(x * x + y * y);
        x /= len;
        y /= len;
    }

    public void mult(double factor) {
        x *= factor;
        y *= factor;
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }


    public void limit(double max) {
        double len = Math.sqrt(x * x + y * y);
        if (len > max) {
            normalize();
            mult(max);
        }
    }

    public double heading() {
        return Math.atan2(y, x);
    }
}
