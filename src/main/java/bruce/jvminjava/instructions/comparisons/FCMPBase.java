package bruce.jvminjava.instructions.comparisons;

import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public abstract class FCMPBase {
    public static void compare(Frame frame, boolean gflag) {
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
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
