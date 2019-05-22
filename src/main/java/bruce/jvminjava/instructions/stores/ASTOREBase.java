package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Object;

public class ASTOREBase {
    public static void store(Frame frame, int index) {
        Object val = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, val);
    }
}
