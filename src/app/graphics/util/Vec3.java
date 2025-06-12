package app.graphics.util;

public class Vec3 {
    public float x, y, z;
    public Vec3() {
        x = y = z = 0.0f;
    }
    public Vec3(float f) {
        x = y = z = f;
    }
    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec3 neg() {
        return new Vec3(-x, -y, -z);
    }
    public Vec3 add(Vec3 v) {
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }
    public Vec3 sub(Vec3 v) {
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }
    public Vec3 mul(Vec3 v) {
        return new Vec3(x * v.x, y * v.y, z * v.z);
    }
    public Vec3 div(Vec3 v) {
        return new Vec3(x / v.x, y / v.y, z / v.z);
    }
    public Vec3 add(float f) {
        return new Vec3(x + f, y + f, z + f);
    }
    public Vec3 sub(float f) {
        return new Vec3(x - f, y - f, z - f);
    }
    public Vec3 mul(float f) {
        return new Vec3(x * f, y * f, z * f);
    }
    public Vec3 div(float f) {
        return new Vec3(x / f, y / f, z / f);
    }
    public float dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }
    public Vec3 cross(Vec3 v) {
        float nx = y * v.z - z * v.y;
        float ny = z * v.x - x * v.z;
        float nz = x * v.y - y * v.x;
        return new Vec3(nx, ny, nz);
    }
    public float len() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }
    public float lenSq() {
        return x * x + y * y + z * z;
    }
    public Vec3 normalize() {
        float len = this.len();
        float nx, ny, nz;
        if (len == 0) {
            nx = ny = nz = 0.0f;
        }
        else {
            nx = x / len;
            ny = y / len;
            nz = z / len;
        }
        return new Vec3(nx, ny, nz);
    }
    public void copy(Vec3 other) {
        if (other == null) {
            throw new RuntimeException("copy() method argument \"other\" cannot be a null reference.");
        }
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    @Override
    public String toString() {
        return String.format("%f\n%f\n%f", x, y, z);
    }
}
