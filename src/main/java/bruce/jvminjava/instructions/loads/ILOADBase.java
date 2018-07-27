package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.rtda.Frame;

public abstract class ILOADBase {
    public static void iload(Frame frame, int index) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
