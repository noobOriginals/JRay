package app.graphics;

import app.graphics.util.Interval;

public interface Hittable {
    public boolean hitRay(Ray ray, Interval rayT, HitPoint hitPoint);
}