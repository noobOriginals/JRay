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

    public Vec3 getOrigin() {
        return origin;
    }
    public Vec3 getDir() {
        return direction;
    }
}
