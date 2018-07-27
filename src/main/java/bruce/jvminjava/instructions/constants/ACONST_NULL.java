package bruce.jvminjava.instructions.constants;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;

public class ACONST_NULL extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        frame.getOperandStack().pushRef(null);
    }

}
