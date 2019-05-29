package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class ARRAY_LENGTH extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        Array arrRef = (Array) stack.popRef();
        if (arrRef == null) {
            throw new RuntimeException("null pointer exception"); 
        }
        int arrLength = arrRef.getArrayLength();
        stack.pushInt(arrLength);
    }

}
