package app.launcher;

import app.graphics.Render;
import app.graphics.Sphere;
import app.graphics.World;
import app.graphics.materials.Dielectric;
import app.graphics.materials.Lambertian;
import app.graphics.materials.Metal;
import app.graphics.Material;
import app.graphics.util.Vec3;

public class Main {
    public static void main(String[] args) {
        Render render = new Render(new Vec3(-2.0f, 2.0f, 1.0f), new Vec3(0.0f, 0.0f, -1.0f), 2000, 1200, 70.0f, 500, 100, 32);
        World world = new World();
        world.add(new Sphere(new Vec3(0.0f, -100.5f, -1.0f), 100.0f, diffuse(0.8f, 0.8f, 0.0f)));
        world.add(new Sphere(new Vec3(0.0f, 0.0f, -1.2f), 0.5f, diffuse(0.1f, 0.2f, 0.5f)));
        world.add(new Sphere(new Vec3(-1.0f, 0.0f, -1.0f), 0.4f, dielectric(0.9f, 0.9f, 0.9f, 1.0f / 1.5f)));
        world.add(new Sphere(new Vec3(-1.0f, 0.0f, -1.0f), 0.5f, dielectric(0.9f, 0.9f, 0.9f, 1.5f)));
        world.add(new Sphere(new Vec3(1.0f, 0.0f, -1.0f), 0.5f, metal(0.8f, 0.4f, 0.4f, 0.08f)));
        world.add(new Sphere(new Vec3(1.5f, -0.2f, -2.3f), 0.3f, metal(0.5f, 0.7f, 0.9f, 0.01f)));
        render.render(world);
        render.waitToFinish();
        System.out.println("Saving render...");
        long startTime = System.nanoTime(), endTime;
        render.save("render.bmp");
        endTime = System.nanoTime();
        System.out.println("Saved render in " + (endTime - startTime) / 1000000000.0f + " seconds.");
        System.out.println("Render complete.");
    }

    public static Material diffuse(float r, float g, float b) {
        return new Lambertian(new Vec3(r, g, b));
    }
    public static Material metal(float r, float g, float b, float fuzz) {
        return new Metal(new Vec3(r, g, b), fuzz);
    }
    public static Material dielectric(float r, float g, float b, float refractIdx) {
        return new Dielectric(new Vec3(r, g, b), refractIdx);
    }
}
