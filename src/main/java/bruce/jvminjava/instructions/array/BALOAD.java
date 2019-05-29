package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class BALOAD extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        Utils.checkNull(arrRef);
        
        byte[] bytes = arrRef.getBytes();
        
        Utils.checkIndex(bytes.length, index);
        
        stack.pushInt(bytes[index]);
    }
    
    

}
