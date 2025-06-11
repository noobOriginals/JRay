package app.launcher;

import app.graphics.Render;
import app.graphics.Sphere;
import app.graphics.World;
import app.graphics.util.Vec3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Strarting render...");
        long startTime = System.nanoTime();
        Render render = new Render(800, 450, 1.0f, 4.0f, 50, 2);
        World world = new World();
        world.add(new Sphere(new Vec3(0.0f, 0.0f, -1.0f), 0.5f));
        world.add(new Sphere(new Vec3(0, -100.5f, -1.0f), 100.0f));
        render.render(world);
        long endTime = System.nanoTime();
        System.out.println("Rendered " + render.getWidth() * render.getHeight() + " pixels in " + (endTime - startTime) / 1000000000.0f + " seconds.");
        startTime = System.nanoTime();
        render.save("render.bmp");
        endTime = System.nanoTime();
        System.out.println("Saved render in " + (endTime - startTime) / 1000000000.0f + " seconds.");
        System.out.println("Render complete.");
    }
}
