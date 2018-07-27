package bruce.jvminjava.instructions.comparisons;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class LCMP extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();
        if (v1 > v2) {
            stack.pushInt(1);
        } else if (v1 == v2) {
            stack.pushInt(0);
        } else {
            stack.pushInt(-1);
        }
    }

}
