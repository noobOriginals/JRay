package app.graphics;

import app.graphics.util.Vec3;

public interface Material {
    public boolean scatter(Ray rayIn, HitPoint hitPoint, Vec3 attenuation, Ray scatteredRay);
}
