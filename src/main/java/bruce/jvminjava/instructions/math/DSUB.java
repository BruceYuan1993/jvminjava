package bruce.jvminjava.instructions.math;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class DSUB extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        double v1 = stack.popDouble();
        double v2 = stack.popDouble();
        double result = v1 - v2;
        stack.pushDouble(result);
    }

}
