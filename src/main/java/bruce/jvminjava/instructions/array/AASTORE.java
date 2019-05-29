package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Object;

public class AASTORE extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        Object val = stack.popRef();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        
        Utils.checkNull(arrRef);
        Object[] refs = arrRef.getRefs();
        Utils.checkIndex(refs.length, index);
        refs[index] = val;
    }

}
