package app.graphics;

import app.graphics.util.Vec3;

public class HitPoint {
    private Vec3 point;
    private Vec3 normal;
    private Material material;
    private float t;
    private boolean frontFace;
    public void setFaceNormal(Ray ray, Vec3 outwardNormal) {
        frontFace = ray.getDir().dot(outwardNormal) < 0.0f;
        normal = frontFace ? outwardNormal : outwardNormal.neg();
    }
    public void setPoint(Vec3 point) {
        this.point = point;
    }
    public void setNormal(Vec3 normal) {
        this.normal = normal;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public void setT(float t) {
        this.t = t;
    }
    public void setFrontFace(boolean frontFace) {
        this.frontFace = frontFace;
    }
    public Vec3 getPoint() {
        return point;
    }
    public Vec3 getNormal() {
        return normal;
    }
    public Material getMaterial() {
        return material;
    }
    public float getT() {
        return t;
    }
    public boolean getFrontFace() {
        return frontFace;
    }
    public void copy(HitPoint other) {
        if (other == null) {
            throw new RuntimeException("copy() method argument \"other\" cannot be a null reference.");
        }
        this.point = other.point;
        this.normal = other.normal;
        this.material = other.material;
        this.t = other.t;
        this.frontFace = other.frontFace;
    }
}
