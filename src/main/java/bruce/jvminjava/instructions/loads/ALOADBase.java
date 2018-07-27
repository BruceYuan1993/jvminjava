package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.rtda.Frame;

public abstract class ALOADBase {
    public static void load(Frame frame, int index) {
        Object val = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(val);
    }
}
