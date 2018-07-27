package bruce.jvminjava.instructions.comparisons;

import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public abstract class DCMPBase {
    public static void compare(Frame frame, boolean gflag) {
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        if (v1 > v2) {
            stack.pushInt(1);
        } else if (v1 == v2) {
            stack.pushInt(0);
        } else if (v1 < v2) {
            stack.pushInt(-1);
        } else if (gflag) {
            stack.pushInt(1);
        } else {
            stack.pushInt(-1);
        }
    }
}
