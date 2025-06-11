package app.graphics;

import app.graphics.util.Vec3;

public class HitPoint {
    public Vec3 point;
    public Vec3 normal;
    public float t;
    public boolean frontFace;
    public void setFaceNormal(Ray ray, Vec3 outwardNormal) {
        frontFace = ray.getDir().dot(outwardNormal) < 0.0f;
        normal = frontFace ? outwardNormal : outwardNormal.neg();
    }
    public Vec3 getPoint() {
        return point;
    }
    public Vec3 getNormal() {
        return normal;
    }
    public float getT() {
        return t;
    }
    public boolean getFrontFace() {
        return frontFace;
    }
}
