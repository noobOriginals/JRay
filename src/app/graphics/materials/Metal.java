package app.graphics.materials;

import app.graphics.util.Vec3;
import app.graphics.HitPoint;
import app.graphics.Material;
import app.graphics.Ray;

import static app.graphics.util.Utility.*;

public class Metal implements Material {
    private Vec3 albedo;
    private float fuzz;

    public Metal(Vec3 albedo, float fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz;
    }

    @Override
    public boolean scatter(Ray rayIn, HitPoint hitPoint, Vec3 attenuation, Ray scatteredRay) {
        Vec3 direction = reflect(rayIn.getDir(), hitPoint.getNormal());
        direction = direction.normalize().add(randomUnitVec3().mul(fuzz));
        scatteredRay.setOrigin(hitPoint.getPoint());
        scatteredRay.setDir(direction);
        attenuation.copy(albedo);
        return (direction.dot(hitPoint.getNormal()) > 0.0f);
    }

    public Vec3 getAlbedo() {
        return albedo;
    }
    public float getFuzz() {
        return fuzz;
    }
}
