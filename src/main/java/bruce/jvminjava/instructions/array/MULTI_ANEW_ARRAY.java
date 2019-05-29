package bruce.jvminjava.instructions.array;


import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ClassReference;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Object;

public class MULTI_ANEW_ARRAY implements Instruction{
    
    private int index;
    private short dimensions;
    
    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readInt16();
        this.dimensions = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        ClassReference classRef = (ClassReference) cp.getConstant(index);
        Class arrClass = classRef.resolvedClass();
        
        OperandStack stack = frame.getOperandStack();
        int[] counts  = popAndCheckCounts(stack, dimensions);
        Array arr = newMultidimensionalArray(counts, arrClass);
        stack.pushRef(arr);
    }
    
    private Array newMultidimensionalArray(int[] counts, Class arrClass) {
        // TODO Auto-generated method stub
        int count = counts[0];
        Array arr = arrClass.newArray(count);
        if (counts.length > 1) {
            Object[] refs = arr.getRefs();
            for (int i = 0; i < refs.length; i++) {
                // TODO
                int[] newCounts = new int[counts.length - 1];
                for (int j = 1; j < counts.length; j++) {
                    newCounts[j - 1] = counts[j]; 
                }
                refs[i] = newMultidimensionalArray(newCounts, arrClass.getCompomentClass());
            }
        }
        return arr;
    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new RuntimeException("NegativeArraySizeException");
            }
        }
        
        return counts;
    }

}
