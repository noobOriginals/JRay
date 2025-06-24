package app.graphics.materials;

import static app.graphics.util.Utility.randomFloat;
import static app.graphics.util.Utility.reflect;
import static app.graphics.util.Utility.reflectance;
import static app.graphics.util.Utility.refract;

import app.graphics.HitPoint;
import app.graphics.Material;
import app.graphics.Ray;
import app.graphics.util.Vec3;

public class Dielectric implements Material {
    private final Vec3 albedo;
    private final float refractionIdx;

    public Dielectric(Vec3 albedo, float refractionIdx) {
        this.albedo = albedo;
        this.refractionIdx = refractionIdx;
    }

    @Override
    public boolean scatter(Ray rayIn, HitPoint hitPoint, Vec3 attenuation, Ray scatteredRay) {
        attenuation.copy(albedo);
        float ratio = hitPoint.getFrontFace() ? (1.0f / refractionIdx) : refractionIdx;

        Vec3 direction = rayIn.getDir().normalize();

        float cos = Math.min(direction.neg().dot(hitPoint.getNormal()), 1.0f);
        float sin = (float)Math.sqrt(1.0f - cos * cos);

        boolean cannotRefract = ratio * sin > 1.0f;

        if (cannotRefract || reflectance(cos, ratio) > randomFloat()) {
            direction = reflect(direction, hitPoint.getNormal());
        }
        else {
            direction = refract(direction, hitPoint.getNormal(), ratio);
        }

        scatteredRay.setOrigin(hitPoint.getPoint());
        scatteredRay.setDir(direction);
        return true;
    }

    public float getRefractionIdx() {
        return refractionIdx;
    }

}
