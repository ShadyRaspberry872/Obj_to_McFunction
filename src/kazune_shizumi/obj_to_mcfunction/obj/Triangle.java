package kazune_shizumi.obj_to_mcfunction.obj;

public class Triangle {

    public final IndicesGroup[] indicesGroups;
    public final String materialId;

    public Triangle(IndicesGroup[] indicesGroups, String materialId) {
        this.indicesGroups = indicesGroups;
        this.materialId = materialId;
    }

    public static class IndicesGroup {

        public final int vertexIndex;
        public final int uvIndex;

        public IndicesGroup(String indices) {
            this(indices.split("/"));
        }

        public IndicesGroup(String[] indices) {
            vertexIndex = Integer.parseInt(indices[0]);
            if (!indices[2].isBlank()) uvIndex = Integer.parseInt(indices[2]);
            else uvIndex = -1;
        }
    }
}
