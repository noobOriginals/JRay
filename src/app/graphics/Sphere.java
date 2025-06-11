package app.graphics;

import app.graphics.util.Interval;
import app.graphics.util.Vec3;

public class Sphere implements Hittable {
    private Vec3 center;
    private float radius;

    public Sphere(Vec3 center, float radius) {
        this.center = center;
        this.radius = (radius > 0.0f) ? radius : 0.0f;
    }

    @Override
    public boolean hitRay(Ray ray, Interval rayT, HitPoint hitPoint) {
        if (hitPoint == null) {
            throw new RuntimeException("hitRay() method argument \"hitPoint\" cannot be a null reference.");
        }

        Vec3 oc = center.sub(ray.getOrigin());
        float a = ray.getDir().lenSq();
        float h = ray.getDir().dot(oc);
        float c = oc.lenSq() - radius * radius;
        float delta = h * h - a * c;
        if (delta < 0.0f) {
            return false;
        }

        delta = (float)Math.sqrt(delta);
        float root = (h - delta) / a;
        if (!rayT.surrounds(root)) {
            root = (h + delta) / a;
            if (!rayT.surrounds(root)) {
                return false;
            }
        }

        hitPoint.t = root;
        hitPoint.point = ray.at(hitPoint.t);
        Vec3 normal = hitPoint.getPoint().sub(center).normalize();
        hitPoint.setFaceNormal(ray, normal);

        return true;
    }

    public Vec3 getCenter() {
        return center;
    }
    public float getRadius() {
        return radius;
    }
}
