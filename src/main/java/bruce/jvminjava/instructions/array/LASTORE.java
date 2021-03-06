package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class LASTORE extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        long val = stack.popLong();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        
        Utils.checkNull(arrRef);
        long[] longs = arrRef.getLongs();
        Utils.checkIndex(longs.length, index);
        longs[index] = val;
    }

}
