package bruce.jvminjava.instructions.stack;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.Slot;

public class DUP extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot(); 
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }

}
