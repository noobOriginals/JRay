package app.launcher;

import app.graphics.Render;
import app.graphics.Sphere;
import app.graphics.World;
import app.graphics.materials.Lambertian;
import app.graphics.materials.Metal;
import app.graphics.Material;
import app.graphics.util.Vec3;

public class Main {
    static Material fuzzedMetal = new Metal(new Vec3(0.8f, 0.6f, 0.2f), 0.2f);
    static Material diffuse = new Lambertian(new Vec3(0.1f, 0.2f, 0.5f));
    static Material shinyMetal = new Metal(new Vec3(0.8f, 0.8f, 0.0f), 0.05f);
    public static void main(String[] args) {
        Render render = new Render(800, 450, 1.0f, 4.0f, 500, 100, 64);
        // Render render = new Render(1920, 1080, 1.0f, 4.0f, 200, 100, 128);
        // Render render = new Render(3840, 2160, 1.0f, 4.0f, 500, 100, 128);
        World world = new World();
        world.add(new Sphere(new Vec3(0, -100.5f, -1.0f), 100.0f, diffuse(20, 130, 20)));
        world.add(new Sphere(new Vec3(0.0f, 0.0f, -1.3f), 0.5f, diffuse(25, 51, 127)));
        world.add(new Sphere(new Vec3(-0.8f, 0.0f, -1.0f), 0.5f, metal(255, 255, 255, 1.0f)));
        world.add(new Sphere(new Vec3(0.2f, 0.9f, -1.5f), 0.5f, metal(200, 100, 100, 8.0f)));
        render.render(world);
        while (!render.isDone()) {}
        System.out.println("Saving render...");
        long startTime = System.nanoTime(), endTime;
        render.save("render.bmp");
        endTime = System.nanoTime();
        System.out.println("Saved render in " + (endTime - startTime) / 1000000000.0f + " seconds.");
        System.out.println("Render complete.");
    }

    public static Material diffuse(int r, int g, int b) {
        return new Lambertian(new Vec3(r / 255.0f, g / 255.0f, b / 255.0f));
    }
    public static Material metal(int r, int g, int b, float fuzz) {
        return new Metal(new Vec3(r / 255.0f, g / 255.0f, b / 255.0f), fuzz / 100.0f);
    }
}
