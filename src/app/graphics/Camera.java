package app.graphics;

import app.graphics.util.Vec3;

import static app.graphics.util.VecMath.*;

public class Camera {
    private float aspectRatio;
    private float focalLength;
    private float viewportWidth;
    private float viewportHeight;
    private Vec3 pixelDeltaX;
    private Vec3 pixelDeltaY;
    private Vec3 pixelOrigin;
    private Vec3 pos;

    public Camera(Vec3 pos, float aspectRatio, float focalLength, float viewportWidth, float imageWidth) {
        this.pos = pos;
        this.aspectRatio = aspectRatio;
        this.focalLength = focalLength;
        this.viewportWidth = viewportWidth;
        int imageHeight = (int)(imageWidth / aspectRatio);
        viewportHeight = viewportWidth * (float)imageHeight / imageWidth;
        Vec3 viewPortX = new Vec3(viewportWidth,0.0f, 0.0f);
        Vec3 viewPortY = new Vec3(0.0f, viewportHeight, 0.0f);
        pixelDeltaX = viewPortX.div(imageWidth);
        pixelDeltaY = viewPortY.div(imageHeight);
        Vec3 viewPortOrigin = pos.sub(new Vec3(0.0f, 0.0f, focalLength)).sub(viewPortX.div(2.0f)).sub(viewPortY.div(2.0f));
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
}
