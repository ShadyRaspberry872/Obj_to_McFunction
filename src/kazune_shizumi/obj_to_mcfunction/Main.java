package kazune_shizumi.obj_to_mcfunction;

import kazune_shizumi.obj_to_mcfunction.math.Vector3;
import kazune_shizumi.obj_to_mcfunction.obj.Obj;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Arguments.parse(args);
        Arguments.assertArguments();
        Arguments.print();

        Obj obj = new Obj(Arguments.input);

        if (Arguments.autoDimensions == 0) {
            Arguments.height = Math.round(obj.sizeY * Arguments.width / obj.sizeX);
            Arguments.depth = Math.round(obj.sizeZ * Arguments.width / obj.sizeX);
        }
        if (Arguments.autoDimensions == 1) {
            Arguments.width = Math.round(obj.sizeX * Arguments.height / obj.sizeY);
            Arguments.depth = Math.round(obj.sizeZ * Arguments.height / obj.sizeY);
        }
        if (Arguments.autoDimensions == 2) {
            Arguments.width = Math.round(obj.sizeX * Arguments.depth / obj.sizeZ);
            Arguments.height = Math.round(obj.sizeY * Arguments.depth / obj.sizeZ);
        }
        Console.println(Console.GREEN + "Resulting structure size: " + Console.RESET +
                "width: " + Console.CYAN + Arguments.width + Console.RESET + ", " +
                "height: " + Console.CYAN + Arguments.height + Console.RESET + ", " +
                "depth: " + Console.CYAN + Arguments.depth
        );

        obj.offset(new Vector3(-obj.minX, -obj.minY, -obj.minZ));
        obj.scale(new Vector3(Arguments.width / obj.sizeX, Arguments.height / obj.sizeY, Arguments.depth / obj.sizeZ));
    }
}