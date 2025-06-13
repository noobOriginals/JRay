package app.graphics;

import app.graphics.util.Image;
import app.graphics.util.Interval;
import app.graphics.util.Pixel;
import app.graphics.util.Vec3;

import static app.graphics.util.VecMath.*;
import static app.graphics.util.Utility.*;

public class Render {
    private Camera camera;
    private Image image;
    private int samplesPerPixel;
    private float pixelSamplesScale;
    private int maxDepth;

    public Render(int width, int height, float focalLength, float viewPortWidth, int samplesPerPixel, int maxDepth) {
        float aspectRatio = (float)width / height;
        camera = new Camera(new Vec3(0.0f, 0.0f, 0.0f), aspectRatio, focalLength, viewPortWidth, width);
        image = new Image(width, height);
        this.samplesPerPixel = samplesPerPixel;
        this.maxDepth = maxDepth;
        pixelSamplesScale = 1.0f / samplesPerPixel;
    }

    public void render(World world) {
        System.out.println("Starting render...");
        long startTime = System.nanoTime();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Vec3 color = new Vec3(0.0f, 0.0f, 0.0f);
                for (int s = 0; s < samplesPerPixel; s++) {
                    Vec3 offset = new Vec3(randomFloat(-0.5f, 0.5f), randomFloat(-0.5f, 0.5f), 0.0f);
                    Vec3 pixelPos = camera.getPixelPos(x, y).add(offset.mul(new Vec3(camera.getPixelDeltaX().x, camera.getPixelDeltaY().y, 0.0f)));
                    Ray ray = new Ray(camera.getPos(), pixelPos.sub(camera.getPos()).normalize());
                    color = add(color, raycast(ray, maxDepth, world));
                }
                image.set(x, y, new Pixel(gammaCorrect(clamp(color.mul(pixelSamplesScale), 0.0f, 1.0f))));
                int p = (int)((float)(y * image.getWidth() + x) / image.getSize() * 100.0f);
                long elapsedTime = System.nanoTime() - startTime;
                System.out.print("Rendering scanline: " + y + ", pixel: " + (y * image.getWidth() + x) + ", progress: " + p + "%, time: " + elapsedTime / 1000000000.0f + "s           \r");
            }
        }
        System.out.println("Rendered " + image.getSize() + " pixels in: " + (System.nanoTime() - startTime) / 1000000000.0f + " seconds.                                                                                       ");
    }

    public void save(String filename) {
        image.save(filename);
    }

    public static Vec3 raycast(Ray ray, int depth, World world) {
        if (depth <= 0) {
            return new Vec3(0.0f);
        }
        HitPoint hitPoint = new HitPoint();
        Vec3 color;
        if (world.raycast(ray, new Interval(0.001f, Float.POSITIVE_INFINITY), hitPoint)) {
            Ray scatteredRay = new Ray();
            Vec3 attenuation = new Vec3();
            if (hitPoint.getMaterial().scatter(ray, hitPoint, attenuation, scatteredRay)) {
                return raycast(scatteredRay, depth - 1, world).mul(attenuation);
            } else {
                color = new Vec3(0.0f);
            }
        }
        else {
            Vec3 dir = ray.getDir().normalize();
            float a = dir.y * 0.5f + 0.5f;
            color = new Vec3(1.0f, 1.0f, 1.0f).mul(1.0f - a).add(new Vec3(0.5f, 0.7f, 1.0f).mul(a));
        }
        return color;
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
