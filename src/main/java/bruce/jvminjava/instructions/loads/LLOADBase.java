package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.rtda.Frame;

public abstract class LLOADBase {
    public static void load(Frame frame, int index) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
