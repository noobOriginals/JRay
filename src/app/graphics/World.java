package app.graphics;

import java.util.ArrayList;

import app.graphics.util.Interval;

public class World {
    private ArrayList<Hittable> objects;
    public World() {
        this.objects = new ArrayList<>(0);
    }
    public World(ArrayList<Hittable> objects) {
        if (objects == null) {
            this.objects = new ArrayList<>(0);
        }
        else {
            this.objects = objects;
        }
    }

    public void add(Hittable object) {
        if (object != null) {
            objects.add(object);
        }
    }
    public void remove(int idx) {
        if (idx >= 0 && idx < objects.size()) {
            objects.remove(idx);
        }
    }
    public void clear() {
        objects.clear();
    }

    public boolean raycast(Ray ray, Interval rayT, HitPoint hitPoint) {
        if (hitPoint == null) {
            throw new RuntimeException("hitRay() method argument \"hitPoint\" cannot be a null reference.");
        }
        if (objects.isEmpty()) {
            return false;
        }

        HitPoint tempHit = new HitPoint();
        boolean hit = false;
        float closestT = rayT.getMax();

        for (Hittable object : objects) {
            if (object.hitRay(ray, new Interval(rayT.getMin(), closestT), tempHit)) {
                hit = true;
                closestT = tempHit.t;
                copyHitPoint(tempHit, hitPoint);
            }
        }

        return hit;
    }

    public int getObjectCount() {
        return objects.size();
    }
    public Hittable getObject(int idx) {
        if (idx >= 0 && idx < this.objects.size()) {
            return objects.get(idx);
        }
        return null;
    }
    public ArrayList<Hittable> getObjects() {
        return objects;
    }

    private void copyHitPoint(HitPoint src, HitPoint dest) {
        dest.t = src.getT();
        dest.point = src.getPoint();
        dest.normal = src.getNormal();
        dest.frontFace = src.getFrontFace();
    }
}
