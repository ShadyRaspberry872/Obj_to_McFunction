package kazune_shizumi.obj_to_mcfunction.obj;

import kazune_shizumi.obj_to_mcfunction.math.Vector3;

import java.nio.file.Path;

public class Material {

    public final String id;

    public Vector3 diffuse = new Vector3(1, 1, 1);
    public Path texturePath;

    public Material(String id) {
        this.id = id;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
