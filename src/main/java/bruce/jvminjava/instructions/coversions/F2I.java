package bruce.jvminjava.instructions.coversions;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class F2I extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        float val = stack.popFloat();
        stack.pushInt((int) val);
    }

}
