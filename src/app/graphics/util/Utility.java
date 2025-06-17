package app.graphics.util;

import java.util.Random;

public class Utility {
    private static final ThreadLocal<Random> threadLocalRandom = ThreadLocal.withInitial(Random::new);

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
        return threadLocalRandom.get().nextFloat();
    }
    public static float randomFloat(float min, float max) {
        return min + threadLocalRandom.get().nextFloat() * (max - min);
    }
    public static float randomGaussian() {
        return (float)threadLocalRandom.get().nextGaussian();
    }
    public static float randomGaussian(float mean, float stddev) {
        return (float)(mean + stddev * threadLocalRandom.get().nextGaussian());
    }
    public static Vec3 randomVec3() {
        return new Vec3(randomFloat(), randomFloat(), randomFloat());
    }
    public static Vec3 randomVec3(float min, float max) {
        return new Vec3(randomFloat(min, max), randomFloat(min, max), randomFloat(min, max));
    }
    public static Vec3 randomUnitVec3() {
        return new Vec3(randomGaussian(), randomGaussian(), randomGaussian()).normalize();
    }
    public static Vec3 gammaCorrect(Vec3 color) {
        return new Vec3((float)Math.sqrt(color.x), (float)Math.sqrt(color.y), (float)Math.sqrt(color.z));
    }
    public static boolean isNearZero(float f) {
        return Math.abs(f) < 1e-8f;
    }
    public static boolean isNearZero(Vec3 v) {
        return isNearZero(v.x) && isNearZero(v.y) && isNearZero(v.z);
    }
    public static Vec3 reflect(Vec3 v, Vec3 normal) {
        return v.sub(normal.mul(2.0f * v.dot(normal)));
    }
}
