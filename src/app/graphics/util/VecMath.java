package app.graphics.util;

public class VecMath {
    public static Vec3 neg(Vec3 v) {
        return new Vec3(-v.x, -v.y, -v.z);
    }
    public static Vec3 add(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x + v1.x, v0.y + v1.y, v0.z + v1.z);
    }
    public static Vec3 sub(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x - v1.x, v0.y - v1.y, v0.z - v1.z);
    }
    public static Vec3 mul(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x * v1.x, v0.y * v1.y, v0.z * v1.z);
    }
    public static Vec3 div(Vec3 v0, Vec3 v1) {
        return new Vec3(v0.x / v1.x, v0.y / v1.y, v0.z / v1.z);
    }
    public static Vec3 add(Vec3 v, float f) {
        return new Vec3(v.x + f, v.y + f, v.z + f);
    }
    public static Vec3 sub(Vec3 v, float f) {
        return new Vec3(v.x - f, v.y - f, v.z - f);
    }
    public static Vec3 sub(float f, Vec3 v) {
        return new Vec3(f - v.x, f - v.y, f - v.z);
    }
    public static Vec3 mul(Vec3 v, float f) {
        return new Vec3(v.x * f, v.y * f, v.z * f);
    }
    public static Vec3 div(Vec3 v, float f) {
        return new Vec3(v.x / f, v.y / f, v.z / f);
    }
    public static Vec3 div(float f, Vec3 v) {
        return new Vec3(f / v.x, f / v.y, f / v.z);
    }
    public static float dot(Vec3 v0, Vec3 v1) {
        return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
    }
    public static Vec3 cross(Vec3 v0, Vec3 v1) {
        float x = v0.y * v1.z - v0.z * v1.y;
        float y = v0.z * v1.x - v0.x * v1.z;
        float z = v0.x * v1.y - v0.y * v1.x;
        return new Vec3(x, y, z);
    }
    public static float len(Vec3 v) {
        return (float)Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }
    public static float lenSq(Vec3 v) {
        return v.x * v.x + v.y * v.y + v.z * v.z;
    }
    public static Vec3 normalize(Vec3 v) {
        float len = v.len();
        if (len == 0) {
            return new Vec3(0, 0, 0);
        }
        return new Vec3(v.x / len, v.y / len, v.z / len);
    }
    public static String toString(Vec3 v) {
        return String.format("%f\n%f\n%f", v.x, v.y, v.z);
    }
}
