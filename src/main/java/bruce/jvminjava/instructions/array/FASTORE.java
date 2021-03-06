package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;

public class FASTORE extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        float val = stack.popFloat();
        int index = stack.popInt();
        Array arrRef = (Array) stack.popRef();
        
        Utils.checkNull(arrRef);
        float[] floats = arrRef.getFloats();
        Utils.checkIndex(floats.length, index);
        floats[index] = val;
    }

}
