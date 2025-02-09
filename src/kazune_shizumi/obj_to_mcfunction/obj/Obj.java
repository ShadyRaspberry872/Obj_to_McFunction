package kazune_shizumi.obj_to_mcfunction.obj;

import kazune_shizumi.obj_to_mcfunction.Console;
import kazune_shizumi.obj_to_mcfunction.math.Vector2;
import kazune_shizumi.obj_to_mcfunction.math.Vector3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Obj {

    public final List<String> lines;

    public final List<Vector2> uvs = new ArrayList<>();
    public final List<Vector3> vertices = new ArrayList<>();
    public final List<Triangle> triangles = new ArrayList<>();

    public Mtl mtl = null;

    public double minX = Double.MAX_VALUE;
    public double minY = Double.MAX_VALUE;
    public double minZ = Double.MAX_VALUE;
    public double maxX = Double.MIN_VALUE;
    public double maxY = Double.MIN_VALUE;
    public double maxZ = Double.MIN_VALUE;
    public double sizeX = Double.NEGATIVE_INFINITY;
    public double sizeY = Double.NEGATIVE_INFINITY;
    public double sizeZ = Double.NEGATIVE_INFINITY;

    public Obj(Path path) throws IOException {
        this(Files.readAllLines(path));
    }

    public Obj(List<String> lines) {
        this.lines = lines;
        parseData();
        getBounding();
    }

    private void parseData() {
        Console.println(Console.GREEN + "OBJ: " + Console.RESET + "Parsing " + Console.CYAN + lines.size() + Console.RESET + " lines");
        String currentMaterialId = null;
        for (String line : lines) {
            if (line.startsWith("vt ")) parseUV(line);
            if (line.startsWith("v ")) parseVertex(line);
            if (line.startsWith("f ")) parseFace(line, currentMaterialId);
            if (line.startsWith("usemtl ")) currentMaterialId = line.substring(7);
        }
        Console.println(Console.GREEN + "OBJ: " + Console.RESET +
                "UVs: " + Console.CYAN + uvs.size() + Console.RESET + ", " +
                "Vertices: " + Console.CYAN + vertices.size() + Console.RESET + ", " +
                "Triangles: " + Console.CYAN + triangles.size()
        );
    }

    private void getBounding() {
        for (Vector3 vertex : vertices) {
            minX = Math.min(minX, vertex.x);
            minY = Math.min(minY, vertex.y);
            minZ = Math.min(minZ, vertex.z);
            maxX = Math.max(maxX, vertex.x);
            maxY = Math.max(maxY, vertex.y);
            maxZ = Math.max(maxZ, vertex.z);
        }
        sizeX = maxX - minX;
        sizeY = maxY - minY;
        sizeZ = maxZ - minZ;
        Console.println(Console.GREEN + "OBJ: " + Console.RESET + "Mesh bounding from (" +
                Console.CYAN + minX + Console.RESET + ", " +
                Console.CYAN + minY + Console.RESET + ", " +
                Console.CYAN + minZ + Console.RESET +
                ") to (" +
                Console.CYAN + maxX + Console.RESET + ", " +
                Console.CYAN + maxY + Console.RESET + ", " +
                Console.CYAN + maxZ + Console.RESET +
                ")"
        );
        Console.println(Console.GREEN + "OBJ: " + Console.RESET + "Mesh size: (" +
                Console.CYAN + sizeX + Console.RESET + ", " +
                Console.CYAN + sizeY + Console.RESET + ", " +
                Console.CYAN + sizeZ + Console.RESET +
                ")"
        );
    }

    private void parseUV(String line) {
        double[] textureCoordinates = Arrays.stream(line.substring(3).split(" ")).mapToDouble(Double::parseDouble).toArray();
        Vector2 vector2 = new Vector2(textureCoordinates);
        uvs.add(vector2);
    }

    private void parseVertex(String line) {
        double[] coordinates = Arrays.stream(line.substring(2).split(" ")).mapToDouble(Double::parseDouble).toArray();
        Vector3 vector3 = new Vector3(coordinates);
        vertices.add(vector3);
    }

    private void parseFace(String line, String materialId) {
        List<Triangle.IndicesGroup> indicesGroups = new ArrayList<>();
        for (String string : line.substring(2).split(" ")) {
            indicesGroups.add(new Triangle.IndicesGroup(string));
        }
        Triangle.IndicesGroup firstGroup = indicesGroups.getFirst();
        for (int i = 1; i < indicesGroups.size() - 1; i++) {
            triangles.add(new Triangle(new Triangle.IndicesGroup[] { firstGroup, indicesGroups.get(i), indicesGroups.get(i + 1) }, materialId));
        }
    }

    public void offset(Vector3 vector3) {
        for (Vector3 vertex : vertices) {
            vertex.addSelf(vector3);
        }
    }

    public void scale(Vector3 vector3) {
        for (Vector3 vertex : vertices) {
            vertex.multiplySelf(vector3);
        }
    }
}
