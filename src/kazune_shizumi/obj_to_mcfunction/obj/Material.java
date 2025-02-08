package kazune_shizumi.obj_to_mcfunction.obj;

public class Material {

    public final String id;

    public Material(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
