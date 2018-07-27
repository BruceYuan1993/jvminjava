package bruce.jvminjava.instructions.constants;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;

public class FCONST_2 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        frame.getOperandStack().pushFloat(2.0f);
    }

}