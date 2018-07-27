package bruce.jvminjava.instructions.stack;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.Slot;

public class SWAP extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot(); 
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
    }

}
