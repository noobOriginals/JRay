package app.graphics;

import app.graphics.util.Vec3;

public class Sphere implements Hittable {
    private Vec3 center;
    private float radius;

    public Sphere(Vec3 center, float radius) {
        this.center = center;
        this.radius = (radius > 0.0f) ? radius : 0.0f;
    }

    @Override
    public boolean hitRay(Ray ray, float tMin, float tMax, HitPoint hitPoint) {
        if (tMax < 0.0f) {
            tMax = Float.MAX_VALUE;
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
        if (root <= tMin || root >= tMax) {
            root = (h + delta) / a;
            if (root <= tMin || root >= tMax) {
                return false;
            }
        }

        hitPoint.t = root;
        hitPoint.point = ray.at(hitPoint.t);
        Vec3 normal = hitPoint.point.sub(center).div(radius);
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
