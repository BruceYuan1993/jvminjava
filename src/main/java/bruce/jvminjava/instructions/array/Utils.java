package bruce.jvminjava.instructions.array;

public class Utils {
    public static void checkNull(Object arrRef) {
        if (arrRef == null) {
            throw new RuntimeException("null pointer exception"); 
        }
    }
    
    
    public static void checkIndex(int arrLen, int index) {
        if (index < 0 || index >= arrLen) {
            throw new RuntimeException("array index our of bounds exception");
        }
    }
    

}
