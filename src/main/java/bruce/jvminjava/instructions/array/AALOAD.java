package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Object;

public class AALOAD extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        Utils.checkNull(arrRef);
        
        Object[] refs = arrRef.getRefs();
        Utils.checkIndex(refs.length, index);
        stack.pushRef(refs[index]);
    }
    
    

}
