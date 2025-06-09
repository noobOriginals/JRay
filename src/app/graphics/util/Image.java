package app.graphics.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Image {
    private Pixel[] pixels;
    private int width, height, size;
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.size = width * height;
        pixels = new Pixel[size];
        for (int i = 0; i < size; i++) {
            pixels[i] = new Pixel(0, 0, 0);
        }
    }
    public Pixel get(int x, int y) {
        return pixels[y * width + x];
    }
    public void set(int x, int y, Pixel p) {
        pixels[y * width + x] = p;
    }
    public void setPixels(int size, Pixel[] pixels) {
        for (int i = 0; i < size; i++) {
            this.pixels[i] = pixels[i];
        }
    }
    public void save(String filename) {
        try {
            OutputStream stream = new FileOutputStream(filename);
            stream.write(toBytes(0x4D42, 2)); // "BM" file header
            stream.write(toBytes(14 + 40 + size * 3, 4)); // File size in bytes (14 file header size + 40 DIB header size + nr. pixels * 3 bytes per pixel)
            stream.write(toBytes(0, 2)); // Ignored
            stream.write(toBytes(0, 2)); // Ignored
            stream.write(toBytes(14 + 40, 4)); // Pixel data offset in bytes (14 file header size + 40 DIB header size)
            stream.write(toBytes(40, 4)); // DIB header
            stream.write(toBytes(width, 4)); // Image width in pixels
            stream.write(toBytes(height, 4)); // Image height in pixels
            stream.write(toBytes(1, 2)); // Color planes, must be 1
            stream.write(toBytes(8 * 3, 2)); // Bits per pixel
            stream.write(toBytes(0, 4)); // Compression method, 0 for BI_RGB compression (no compression)
            stream.write(toBytes(size * 3, 4)); // Size of the raw bitmap data
            stream.write(toBytes(0, 4)); // Ignored
            stream.write(toBytes(0, 4)); // Ignored
            stream.write(toBytes(0, 4)); // Ignored
            stream.write(toBytes(0, 4)); // Ignored
            for (int i = 0; i < size; i++) {
                stream.write(pixels[i].b());
                stream.write(pixels[i].g());
                stream.write(pixels[i].r());
            }
            stream.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    private byte[] toBytes(long data, int bytes) {
        byte[] b = new byte[bytes];
        for (int i = 0; i < bytes; i++) {
            b[i] = (byte)(data >> (i * 8));
        }
        return b;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getSize() {
        return size;
    }
    public Pixel[] getPixels() {
        return pixels;
    }
}
