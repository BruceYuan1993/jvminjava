package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class IASTORE extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        
        Utils.checkNull(arrRef);
        int[] ints = arrRef.getInts();
        Utils.checkIndex(ints.length, index);
        ints[index] = val;
    }

}
