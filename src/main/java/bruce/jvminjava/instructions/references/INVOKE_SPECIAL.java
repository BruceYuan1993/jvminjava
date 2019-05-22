package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;

public class INVOKE_SPECIAL extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        frame.getOperandStack().popRef();
    }

}
