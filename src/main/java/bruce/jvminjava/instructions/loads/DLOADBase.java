package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.rtda.Frame;

public abstract class DLOADBase {
    public static void load(Frame frame, int index) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
