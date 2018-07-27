package bruce.jvminjava.instructions.math;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class LNEG extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        long v1 = stack.popLong();
        stack.pushLong(-v1);
    }

}
