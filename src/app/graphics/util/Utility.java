package app.graphics.util;

public class Utility {
    public static float degreesToRadians(float degrees) {
        return degrees * (float)Math.PI / 180.0f;
    }
    public static float radiansToDegrees(float radians) {
        return radians * 180.0f / (float)Math.PI;
    }
    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
    public static Vec3 clamp(Vec3 v, float min, float max) {
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
    public static float randomFloat() {
        return (float)Math.random();
    }
    public static float randomFloat(float min, float max) {
        return min + (float)Math.random() * (max - min);
    }
}
