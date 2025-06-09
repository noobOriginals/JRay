package app.graphics;

import app.graphics.util.Interval;

public interface Hittable {
    boolean hitRay(Ray ray, Interval rayT, HitPoint hitPoint);
}