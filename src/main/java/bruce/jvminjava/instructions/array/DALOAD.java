package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class DALOAD extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        Utils.checkNull(arrRef);
        
        double[] doubles = arrRef.getDoubles();
        
        Utils.checkIndex(doubles.length, index);
        
        stack.pushDouble(doubles[index]);
    }
    
    

}
