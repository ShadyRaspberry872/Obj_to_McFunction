package kazune_shizumi.obj_to_mcfunction;

public class Main {
    public static void main(String[] args) {
        Arguments.parse(args);
        Arguments.assertArguments();
        Arguments.print();
    }
}