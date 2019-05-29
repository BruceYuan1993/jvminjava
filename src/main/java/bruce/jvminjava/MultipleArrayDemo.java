package bruce.jvminjava;

public class MultipleArrayDemo {
    public static void main(String[] args) {
        int[][] test = new int[3][4];
        
        for (int i=0; i < test.length; i++) {
            for (int j=0; j < test[i].length; j++) {
                test[i][j] = i + j;
            }
        }
        System.out.println(test[2][3]);
    }
}
