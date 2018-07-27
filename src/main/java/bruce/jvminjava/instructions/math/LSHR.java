package bruce.jvminjava.instructions.math;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class LSHR extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        long v1 = stack.popLong();
        long result = v1 >> v2;
        stack.pushLong(result);
    }

}
