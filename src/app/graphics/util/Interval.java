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

    public float getMin() {
        return min;
    }
    public float getMax() {
        return max;
    }
}
