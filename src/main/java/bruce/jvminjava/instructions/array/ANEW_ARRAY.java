package bruce.jvminjava.instructions.array;


import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ClassReference;
import bruce.jvminjava.rtda.heap.ConstantPool;


public class ANEW_ARRAY extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        ClassReference classRef = (ClassReference) cp.getConstant(getIndex());
        Class compontentClass = classRef.resolvedClass();
        
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new RuntimeException("NegativeArraySizeException");
        }
        Class arrayClass = compontentClass.getArrayClass();
        Array array = arrayClass.newArray(count);
        
        stack.pushRef(array);
    }

}
