package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.rtda.Frame;

public abstract class FLOADBase {
    public static void load(Frame frame, int index) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
