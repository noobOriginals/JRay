package app.graphics;

import app.graphics.util.Image;
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

    public void run() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Vec3 pixelPos = camera.getPixelPos(x, y);
                Ray ray = new Ray(camera.getPos(), pixelPos.sub(camera.getPos()).normalize());
                image.set(x, y, raycast(ray));
            }
        }
    }

    public void save(String filename) {
        image.save(filename);
    }

    public static Pixel raycast(Ray r) {
        Sphere s = new Sphere(new Vec3(0.0f, 0.0f, -1.0f), 0.5f);
        HitPoint hitPoint = new HitPoint();
        if (s.hitRay(r, 0.0f, -1.0f, hitPoint)) {
            // Vec3 normal = r.at(hitPoint.t).sub(s.getCenter()).normalize();
            Vec3 normal = hitPoint.normal.normalize();
            Vec3 color = add(new Vec3(1.0f, 1.0f, 1.0f), normal).mul(0.5f);
            return new Pixel(color);
        } else {
            return new Pixel(10, 10, 10);
        }
    }
}
