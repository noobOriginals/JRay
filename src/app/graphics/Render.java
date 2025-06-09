package app.graphics;

import app.graphics.util.Image;
import app.graphics.util.Interval;
import app.graphics.util.Pixel;
import app.graphics.util.Vec3;

import static app.graphics.util.VecMath.*;

public class Render {
    private Camera camera;
    private Image image;

    public Render(int width, int height, float focalLength, float viewPortWidth) {
        float aspectRatio = (float)width / height;
        camera = new Camera(new Vec3(0.0f, 0.0f, 0.0f), aspectRatio, focalLength, viewPortWidth, width);
        image = new Image(width, height);
    }

    public void render(World world) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Vec3 pixelPos = camera.getPixelPos(x, y);
                Ray ray = new Ray(camera.getPos(), pixelPos.sub(camera.getPos()).normalize());
                image.set(x, y, raycast(ray, world));
            }
        }
    }

    public void save(String filename) {
        image.save(filename);
    }

    public static Pixel raycast(Ray ray, World world) {
        HitPoint hitPoint = new HitPoint();
        if (world.raycast(ray, new Interval(0.0f, Float.POSITIVE_INFINITY), hitPoint)) {
            Vec3 normal = hitPoint.normal.normalize();
            Vec3 color = add(new Vec3(1.0f, 1.0f, 1.0f), normal).mul(0.5f);
            return new Pixel(color);
        }
        else {
            Vec3 dir = ray.getDir().normalize();
            float a = dir.y * 0.5f + 0.5f;
            Vec3 col = new Vec3(1.0f, 1.0f, 1.0f).mul(1.0f - a).add(new Vec3(0.5f, 0.7f, 1.0f).mul(a));
            return new Pixel(col);
        }
    }

    public Camera getCamera() {
        return camera;
    }
    public Image getImage() {
        return image;
    }
    public int getWidth() {
        return image.getWidth();
    }
    public int getHeight() {
        return image.getHeight();
    }
}
