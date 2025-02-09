package kazune_shizumi.obj_to_mcfunction.obj;

import kazune_shizumi.obj_to_mcfunction.Console;
import kazune_shizumi.obj_to_mcfunction.math.Vector3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mtl {

    public final Path path;
    public final List<String> lines;

    public final Map<String, Material> materials = new HashMap<>();
    public final Map<Path, BufferedImage> textures = new HashMap<>();

    public Mtl(Path path) throws IOException {
        this.path = path;
        this.lines = Files.readAllLines(path);
        parseData();
    }

    private void parseData() throws IOException {
        String currentId = null;
        Material currentMaterial = null;
        for (String line : lines) {
            if (line.startsWith("newmtl ")) {
                if (currentMaterial != null) materials.put(currentId, currentMaterial);
                currentId = line.substring(7);
                currentMaterial = new Material(currentId);
            }
            if (line.startsWith("Kd ") && currentMaterial != null) {
                double[] color = Arrays.stream(line.substring(3).split(" ")).mapToDouble(Double::parseDouble).toArray();
                currentMaterial.diffuse = new Vector3(color);
            }
            if (line.startsWith("map_Kd ") && currentMaterial != null) {
                Path texturePath = path.getParent().resolve(line.substring(7)).toRealPath();
                currentMaterial.texturePath = texturePath;
                textures.put(texturePath, ImageIO.read(texturePath.toFile()));
            }
        }
        if (currentMaterial != null) materials.put(currentId, currentMaterial);
        Console.println(Console.GREEN + "MTL: " + Console.RESET +
                "Materials: " + Console.CYAN + materials.size() + Console.RESET + ", " +
                "Textures: " + Console.CYAN + textures.size()
        );
    }
}
