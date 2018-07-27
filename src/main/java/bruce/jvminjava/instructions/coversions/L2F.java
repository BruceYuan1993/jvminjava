package bruce.jvminjava.instructions.coversions;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class L2F extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        long val = stack.popLong();
        stack.pushFloat((float) val);
    }

}
