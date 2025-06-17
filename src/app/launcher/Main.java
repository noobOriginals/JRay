package app.launcher;

import app.graphics.Render;
import app.graphics.Sphere;
import app.graphics.World;
import app.graphics.materials.Lambertian;
import app.graphics.materials.Metal;
import app.graphics.Material;
import app.graphics.util.Vec3;

public class Main {
    static Material fuzzedMetal = new Metal(new Vec3(0.8f, 0.6f, 0.2f), 1.0f);
    static Material diffuse = new Lambertian(new Vec3(0.1f, 0.2f, 0.5f));
    static Material shinyMetal = new Metal(new Vec3(0.8f, 0.8f, 0.0f), 0.05f);
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Render render = new Render(800, 600, 1.0f, 4.0f, 500, 10, 5);
        World world = new World();
        world.add(new Sphere(new Vec3(0, -100.5f, -1.0f), 100.0f, fuzzedMetal));
        world.add(new Sphere(new Vec3(0.0f, 0.0f, -1.3f), 0.5f, diffuse));
        world.add(new Sphere(new Vec3(-0.5f, 0.0f, -1.0f), 0.5f, shinyMetal));
        render.render(world);
        while (!render.isDone()) {}
        System.out.println("Saving render...");
        long startTime = System.nanoTime(), endTime;
        render.save("render.bmp");
        endTime = System.nanoTime();
        System.out.println("Saved render in " + (endTime - startTime) / 1000000000.0f + " seconds.");
        System.out.println("Render complete.");
    }
}
