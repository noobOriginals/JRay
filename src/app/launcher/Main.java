package app.launcher;

import app.graphics.Render;

public class Main {
    public static void main(String[] args) {
        System.out.println("Strarting render...");
        Render render = new Render(1200, 900, 2.0f, 10.0f);
        render.run();
        render.save("render.bmp");
    }
}
