package app.graphics.util;

public class Interval {
    public static final Interval empty = new Interval();
    public static final Interval universe = new Interval(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);

    private float min, max;

    public Interval() {
        this.min = Float.POSITIVE_INFINITY;
        this.max = Float.NEGATIVE_INFINITY;
    }
    public Interval(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getSize() {
        return max - min;
    }
    public boolean contains(float value) {
        return value >= min && value <= max;
    }
    public boolean surrounds(float value) {
        return value > min && value < max;
    }
    public float clamp(float value) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
    public Vec3 clamp(Vec3 v) {
        float x, y, z;
        x = v.x;
        y = v.y;
        z = v.z;
        if (v.x < min) {
            x = min;
        }
        if (v.x > max) {
            x = max;
        }
        if (v.y < min) {
            y = min;
        }
        if (v.y > max) {
            y = max;
        }
        if (v.z < min) {
            z = min;
        }
        if (v.z > max) {
            z = max;
        }
        return new Vec3(x, y, z);
    }

    public float getMin() {
        return min;
    }
    public float getMax() {
        return max;
    }
}
