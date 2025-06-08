package app.graphics;

import app.graphics.util.Vec3;

public class HitPoint {
    public Vec3 point;
    public Vec3 normal;
    public float t;
    public boolean frontFace;
    public void setFaceNormal(Ray ray, Vec3 outwardNormal) {
        frontFace = ray.getDir().dot(outwardNormal) < 0;
        normal = frontFace ? outwardNormal : outwardNormal.neg();
    }
}
