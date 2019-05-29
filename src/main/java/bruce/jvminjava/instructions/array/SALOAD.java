package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class SALOAD extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        Utils.checkNull(arrRef);
        
        short[] shorts = arrRef.getShorts();
        Utils.checkIndex(shorts.length, index);
        stack.pushInt(shorts[index]);
    }
    
    

}
