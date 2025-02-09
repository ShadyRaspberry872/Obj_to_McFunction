# .obj to .mcfunction converter
Convert an .obj file to one or more .mcfunction files to import a model into Minecraft

# 🚨 WARNING: UNFINISHED CODE 🚨
This project is currently in development and incomplete.

Running it will be non-functional, or may produce errors.

# Usage
After downloading the build from Releases or compiling the code yourself, run the converter using the following command:
```sh
java -jar Obj_to_McFunction.jar [arguments]
```
| Argument                                  | Description                                                                                                                                                                          |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `--input <file>` or `-i <file>`           | Specifies the input `.obj` file.                                                                                                                                                     |
| `--output <path>` or `-o <path>`          | Specifies the output path. Optional: defaults to input filename with the `.mcfunction` extension. If multiple `.mcfunction` files are generated, the path is treated as a directory. |
| `--width <value>` or `-w <value>`         | Sets the **width** of the resulting structure (**X-axis** in Minecraft).                                                                                                             |
| `--height <value>` or `-h <value>`        | Sets the **height** of the resulting structure (**Y-axis** in Minecraft).                                                                                                            |
| `--depth <value>` or `-d <value>`         | Sets the **depth** of the resulting structure (**Z-axis** in Minecraft).                                                                                                             |
| `--auto-width <value>` or `-a:w <value>`  | Sets the **width** of the resulting structure and proportionally adjusts the **height** and **depth**.                                                                               |
| `--auto-height <value>` or `-a:h <value>` | Sets the **height** of the resulting structure and proportionally adjusts the **width** and **depth**.                                                                               |
| `--auto-depth <value>` or `-a:d <value>`  | Sets the **depth** of the resulting structure and proportionally adjusts the **width** and **height**.                                                                               |