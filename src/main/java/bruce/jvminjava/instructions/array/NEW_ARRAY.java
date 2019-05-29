package bruce.jvminjava.instructions.array;

import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ClassLoader;

public class NEW_ARRAY implements Instruction {
    static final short AT_BOOLEAN = 4;
    static final short AT_CHAR = 5;
    static final short AT_FLOAT = 6;
    static final short AT_DOUBLE = 7;
    static final short AT_BYTE = 8;
    static final short AT_SHORT = 9;
    static final short AT_INT = 10;
    static final short AT_LONG = 11;
    
    private short atype;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        atype = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new RuntimeException("NegativeArraySizeException");
        }
        
        ClassLoader classLoader = frame.getMethod().getKlass().getLoader();
        Class arrClass = getPrimitiveArrayClass(classLoader, atype);
        Array arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }
    
    Class getPrimitiveArrayClass(ClassLoader classLoader, short atype) {
        switch (atype) {
        case AT_BOOLEAN:
            return classLoader.loadClass("[Z");
        case AT_BYTE:
            return classLoader.loadClass("[B");
        case AT_CHAR:
            return classLoader.loadClass("[C");
        case AT_SHORT:
            return classLoader.loadClass("[S");
        case AT_INT:
            return classLoader.loadClass("[I");
        case AT_LONG:
            return classLoader.loadClass("[J");
        case AT_FLOAT:
            return classLoader.loadClass("[F");
        case AT_DOUBLE:
            return classLoader.loadClass("[D");
        default:
            throw new RuntimeException("Invalid atype");
        }
    }

}
