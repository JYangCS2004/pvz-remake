package model.physics;

public class LocalPhysics {
    private Vector location;
    private Vector acceleration;
    private Vector velocity;

    static final Vector GRAVITY = new Vector(0, 0.2);
    private double defaultStrength = 0.2;
    private Vector gravity = new Vector(GRAVITY.x, GRAVITY.y);

    private double gravMultiplier = 1;

    private int ground;

    public LocalPhysics(int x, int y, int ground) {
        this.ground = ground;
        location = new Vector(x, y);
        acceleration = new Vector(0, 0);
        velocity = new Vector(0, 0);
    }

    public void applyForce(Vector force) {
        acceleration.add(force);
    }

    public void update() {
        gravity.y = gravMultiplier * defaultStrength;

        applyForce(gravity);

        velocity.add(acceleration);
        location.add(velocity);

        if (location.y >= ground) {
            location.y = ground;
            velocity.mult(0);
        }

        acceleration.mult(0);
    }

    public boolean isGrounded() {
        return (int) location.y == ground;
    }

    public double getX() {
        return location.x;
    }

    public double getY() {
        return location.y;
    }

    public int getGround() {
        return ground;
    }

    public void revertGravity() {
        gravMultiplier = 1;
    }

    public void reduceGravity(double percentage) {
        gravMultiplier = Math.min(percentage, gravMultiplier);
    }
}
