package app.graphics;

import app.graphics.util.Vec3;

public class Ray {
    private Vec3 origin;
    private Vec3 direction;

    public Ray() {
        origin = direction = new Vec3();
    }
    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }
    public Vec3 at(float t) {
        return origin.add(direction.mul(t));
    }

    public void setOrigin(Vec3 origin) {
        if (origin == null) {
            throw new RuntimeException("Ray.setOrigin() argument \"origin\" cannot be a null reference.");
        }
        this.origin = origin;
    }
    public void setDir(Vec3 direction) {
        if (direction == null) {
            throw new RuntimeException("Ray.setDir() argument \"direction\" cannot be a null reference.");
        }
        this.direction = direction;
    }
    public Vec3 getOrigin() {
        return origin;
    }
    public Vec3 getDir() {
        return direction;
    }
}
