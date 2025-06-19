package app.graphics;

import app.graphics.util.Vec3;

import static app.graphics.util.Utility.*;
import static app.graphics.util.VecMath.*;

public class Camera {
    private float aspectRatio;
    private float focalLength;
    private float viewportWidth;
    private float viewportHeight;
    private Vec3 pixelDeltaX;
    private Vec3 pixelDeltaY;
    private Vec3 pixelOrigin;
    private Vec3 pos, lookat, up;
    private Vec3 u, v, w;
    private int imageWidth, imageHeight;

    public Camera(Vec3 pos, Vec3 lookat, float aspectRatio, float hfov, int imageWidth) {
        this.pos = pos;
        this.lookat = lookat;
        this.up = new Vec3(0.0f, 1.0f, 0.0f);
        this.aspectRatio = aspectRatio;
        float width = (float)Math.tan(degreesToRadians(hfov) / 2.0f);
        this.viewportWidth = width * 2.0f;
        focalLength = 1.0f;
        this.imageWidth = imageWidth;
        this.imageHeight = (int)(imageWidth / aspectRatio);
        viewportHeight = viewportWidth * (float)imageHeight / imageWidth;
        w = pos.sub(lookat).normalize();
        u = up.cross(w).normalize();
        v = w.cross(u);
        Vec3 viewPortX = u.mul(viewportWidth);
        Vec3 viewPortY = v.mul(viewportHeight);
        pixelDeltaX = viewPortX.div(imageWidth);
        pixelDeltaY = viewPortY.div(imageHeight);
        Vec3 viewPortOrigin = pos.sub(w).sub(viewPortX.div(2.0f)).sub(viewPortY.div(2.0f));
        pixelOrigin = viewPortOrigin.add(add(pixelDeltaX, pixelDeltaY).mul(0.5f));
    }

    public Vec3 getPixelPos(int x, int y) {
        return pixelOrigin.add(pixelDeltaY.mul(y)).add(pixelDeltaX.mul(x));
    }
    public Vec3 getPos() {
        return pos;
    }
    public float getAspectRatio() {
        return aspectRatio;
    }
    public float getFocalLength() {
        return focalLength;
    }
    public float getViewportWidth() {
        return viewportWidth;
    }
    public float getViewportHeight() {
        return viewportHeight;
    }
    public Vec3 getPixelDeltaX() {
        return pixelDeltaX;
    }
    public Vec3 getPixelDeltaY() {
        return pixelDeltaY;
    }
    public Vec3 getPixelOrigin() {
        return pixelOrigin;
    }
    public Vec3 getU() {
        return u;
    }
    public Vec3 getV() {
        return v;
    }
    public Vec3 getW() {
        return w;
    }
    public Vec3 getLookat() {
        return lookat;
    }
    public Vec3 getUp() {
        return up;
    }
    public void setPos(Vec3 pos) {
        this.pos = pos;
        w = pos.sub(lookat).normalize();
        u = up.cross(w).normalize();
        v = w.cross(u);
        Vec3 viewPortX = u.mul(viewportWidth);
        Vec3 viewPortY = v.mul(viewportHeight);
        pixelDeltaX = viewPortX.div(imageWidth);
        pixelDeltaY = viewPortY.div(imageHeight);
        Vec3 viewPortOrigin = pos.sub(new Vec3(0.0f, 0.0f, focalLength)).sub(viewPortX.div(2.0f)).sub(viewPortY.div(2.0f));
        pixelOrigin = viewPortOrigin.add(add(pixelDeltaX, pixelDeltaY).mul(0.5f));
    }
}
