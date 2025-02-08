package kazune_shizumi.obj_to_mcfunction;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Arguments {
    public static Path input = null;
    public static Path output = null;
    public static int width = -1;
    public static int height = -1;
    public static int depth = -1;
    public static int autoDimensions = -1;

    static void print() {
        Console.println(Console.GREEN + "Input: " + Console.RESET + input);
        Console.println(Console.GREEN + "Output: " + Console.RESET + output);
        if (autoDimensions < 0) {
            Console.println(Console.GREEN + "Dimensions: " + Console.RESET +
                    "width: " + Console.CYAN + width + Console.RESET + ", " +
                    "height: " + Console.CYAN + height + Console.RESET + ", " +
                    "depth: " + Console.CYAN + depth
            );
        } else switch (autoDimensions) {
            case 0 -> Console.println(Console.GREEN + "Auto dimensions from " + Console.RESET + "width" + Console.GREEN + ": " + Console.CYAN + width);
            case 1 -> Console.println(Console.GREEN + "Auto dimensions from " + Console.RESET + "height" + Console.GREEN + ": " + Console.CYAN + height);
            case 2 -> Console.println(Console.GREEN + "Auto dimensions from " + Console.RESET + "depth" + Console.GREEN + ": " + Console.CYAN + depth);
        }
    }

    private static void parseArgument(String id, String value) {
        switch (id) {
            case "input", "i" -> {
                input = Paths.get(value);
                if (Files.isDirectory(input))
                    throw new IllegalArgumentException("Input cannot be a directory: " + input);
                if (!input.getFileName().endsWith(".obj")) input = Paths.get(input + ".obj");
                if (!Files.exists(input)) throw new IllegalArgumentException("Input file does not exist: " + input);
            }
            case "output", "o" -> {
                output = Paths.get(value);
                if (!output.getFileName().endsWith(".mcfunction")) output = Paths.get(output + ".mcfunction");
            }
            case "width", "w" -> {
                width = Integer.parseInt(value);
                if (width < 0) throw new IllegalArgumentException("Width cannot be negative: " + width);
            }
            case "height", "h" -> {
                height = Integer.parseInt(value);
                if (height < 0) throw new IllegalArgumentException("Height cannot be negative: " + height);
            }
            case "depth", "d" -> {
                depth = Integer.parseInt(value);
                if (depth < 0) throw new IllegalArgumentException("Depth cannot be negative: " + depth);
            }
            case "auto-width", "a:w" -> {
                width = Integer.parseInt(value);
                if (width < 0) throw new IllegalArgumentException("Width cannot be negative: " + width);
                autoDimensions = 0;
            }
            case "auto-height", "a:h" -> {
                height = Integer.parseInt(value);
                if (height < 0) throw new IllegalArgumentException("Height cannot be negative: " + height);
                autoDimensions = 1;
            }
            case "auto-depth", "a:d" -> {
                depth = Integer.parseInt(value);
                if (depth < 0) throw new IllegalArgumentException("Depth cannot be negative: " + depth);
                autoDimensions = 2;
            }
            default -> Console.println(Console.YELLOW + "Unrecognized parameter id: " + id);
        }
    }

    static void assertArguments() {
        if (input == null) throw new IllegalArgumentException("Missing parameter: --input / -i");
        if (output == null) output = Paths.get(input.getParent() + File.separator + input.getFileName().toString().replaceFirst("[.][^.]+$", "") + ".mcfunction");
        if (autoDimensions < 0) {
            if (width < 0) throw new IllegalArgumentException("Missing parameter: --width / -w");
            if (height < 0) throw new IllegalArgumentException("Missing parameter: --height / -h");
            if (depth < 0) throw new IllegalArgumentException("Missing parameter: --depth / -d");
        }
    }

    static void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String string = args[i];
            if (string.startsWith("--")) {
                String id = string.substring(2);
                String value = null;
                if (id.contains("=")) {
                    String[] arg = id.split("=", 2);
                    id = arg[0];
                    value = arg[1];
                } else if (i + 1 < args.length) {
                    value = args[i + 1];
                    i++;
                }
                parseArgument(id, value);
            } else if (string.startsWith("-")) {
                String id = string.substring(1);
                String value = null;
                if (i + 1 < args.length) {
                    value = args[i + 1];
                    i++;
                }
                parseArgument(id, value);
            }
        }
    }
}
