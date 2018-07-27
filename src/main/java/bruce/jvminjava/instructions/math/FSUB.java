package bruce.jvminjava.instructions.math;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class FSUB extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        float v1 = stack.popFloat();
        float v2 = stack.popFloat();
        float result = v1 - v2;
        stack.pushFloat(result);
    }

}
