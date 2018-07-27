package bruce.jvminjava.instructions.coversions;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class I2D extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        stack.pushDouble((double) val);
    }

}
