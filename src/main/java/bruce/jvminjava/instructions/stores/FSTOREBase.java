package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.rtda.Frame;

public class FSTOREBase {
    public static void store(Frame frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
