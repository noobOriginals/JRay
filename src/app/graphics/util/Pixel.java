package app.graphics.util;

public class Pixel {
    private byte r, g, b;
    public Pixel() {
        r = g = b = 0;
    }
    public Pixel(int x) {
        r = g = b = (byte)x;
    }
    public Pixel(int r, int g, int b) {
        this.r = (byte)r;
        this.g = (byte)g;
        this.b = (byte)b;
    }
    public Pixel(Vec3 v) {
        r = (byte)(v.x * 255.0f);
        g = (byte)(v.y * 255.0f);
        b = (byte)(v.z * 255.0f);
    }

    public byte r() {
        return r;
    }
    public byte g() {
        return g;
    }
    public byte b() {
        return b;
    }
}