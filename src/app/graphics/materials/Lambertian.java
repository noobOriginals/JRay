package app.graphics.materials;

import app.graphics.util.Vec3;
import app.graphics.HitPoint;
import app.graphics.Material;
import app.graphics.Ray;

import static app.graphics.util.Utility.*;

public class Lambertian implements Material {
    private final Vec3 albedo;

    public Lambertian(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, HitPoint hitPoint, Vec3 attenuation, Ray scatteredRay) {
        Vec3 direction = hitPoint.getNormal().add(randomUnitVec3());
        if (isNearZero(direction)) {
            direction = hitPoint.getNormal();
        }
        scatteredRay.setOrigin(hitPoint.getPoint());
        scatteredRay.setDir(direction);
        attenuation.copy(albedo);
        return true;
    }

    public Vec3 getAlbedo() {
        return albedo;
    }
}
