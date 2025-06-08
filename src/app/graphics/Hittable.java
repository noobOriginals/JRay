package app.graphics;

public interface Hittable {
    boolean hitRay(Ray ray, float tMin, float tMax, HitPoint hitPoint);
}