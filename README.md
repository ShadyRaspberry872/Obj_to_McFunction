# .obj to .mcfunction converter
Convert an .obj file to one or more .mcfunction files to import a model into Minecraft

# Usage
After downloading the build from Releases or compiling the code yourself, run the converter using the following command:
```sh
java -jar Obj_to_McFunction.jar [arguments]
```
| Argument                                  | Description                                                                                                                                                                          |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `--input <file>` or `-i <file>`           | Specifies the input `.obj` file.                                                                                                                                                     |
| `--output <path>` or `-o <path>`          | Specifies the output path. Optional: defaults to input filename with the `.mcfunction` extension. If multiple `.mcfunction` files are generated, the path is treated as a directory. |
| `--width <value>` or `-w <value>`         | Sets the **width** of the model (**X-axis** in Minecraft).                                                                                                                           |
| `--height <value>` or `-h <value>`        | Sets the **height** of the model (**Y-axis** in Minecraft).                                                                                                                          |
| `--depth <value>` or `-d <value>`         | Sets the **depth** of the model (**Z-axis** in Minecraft).                                                                                                                           |
| `--auto-width <value>` or `-a:w <value>`  | Sets the **width** of the model and automatically adjusts the **height** and **depth** proportionally.                                                                               |
| `--auto-height <value>` or `-a:h <value>` | Sets the **height** of the model and automatically adjusts the **width** and **depth** proportionally.                                                                               |
| `--auto-depth <value>` or `-a:d <value>`  | Sets the **depth** of the model and automatically adjusts the **width** and **height** proportionally.                                                                               |