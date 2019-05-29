package bruce.jvminjava;

public class PrintArgs {
    public static void main(String[] args) {
        System.out.println("Bruce Test");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
