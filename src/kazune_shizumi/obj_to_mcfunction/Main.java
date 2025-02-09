package kazune_shizumi.obj_to_mcfunction;

import kazune_shizumi.obj_to_mcfunction.math.Vector3;
import kazune_shizumi.obj_to_mcfunction.obj.Mtl;
import kazune_shizumi.obj_to_mcfunction.obj.Obj;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments.parse(args);
        Arguments.assertArguments();
        Arguments.print();

        Obj obj = new Obj(Arguments.input);
        Arguments.autoComputeDimensions(obj.sizeX, obj.sizeY, obj.sizeZ);
        obj.offset(new Vector3(-obj.minX, -obj.minY, -obj.minZ));
        obj.scale(new Vector3(Arguments.width / obj.sizeX, Arguments.height / obj.sizeY, Arguments.depth / obj.sizeZ));

        obj.mtl = getMtl(obj);
    }

    static Mtl getMtl(Obj obj) throws IOException {
        for (String line : obj.lines) {
            if (line.startsWith("mtllib ")) {
                Path mtlPath = Arguments.input.getParent().resolve(line.substring(7));
                Console.println(Console.GREEN + "Materials file: " + Console.YELLOW + mtlPath);
                return new Mtl(mtlPath);
            }
        }
        Console.println(Console.YELLOW + "No .mtl file found");
        return null;
    }
}