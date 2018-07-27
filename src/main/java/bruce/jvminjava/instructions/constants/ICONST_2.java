package bruce.jvminjava.instructions.constants;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;

public class ICONST_2 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        frame.getOperandStack().pushInt(2);
    }

}