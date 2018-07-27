package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.rtda.Frame;

public class ISTOREBase {
    public static void store(Frame frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
