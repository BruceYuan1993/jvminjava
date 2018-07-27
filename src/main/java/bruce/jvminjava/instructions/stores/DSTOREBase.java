package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.rtda.Frame;

public class DSTOREBase {
    public static void store(Frame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
