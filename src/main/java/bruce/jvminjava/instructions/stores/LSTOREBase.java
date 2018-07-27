package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.rtda.Frame;

public class LSTOREBase {
    public static void store(Frame frame, int index) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
