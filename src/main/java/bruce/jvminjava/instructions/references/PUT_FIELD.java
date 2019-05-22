package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Field;
import bruce.jvminjava.rtda.heap.FieldReference;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.Object;

public class PUT_FIELD extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Method currentMethod = frame.getMethod();
        bruce.jvminjava.rtda.heap.Class currentClass = currentMethod.getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        FieldReference fieldRef = (FieldReference) cp.getConstant(getIndex());
        Field field = fieldRef.resolvedField();
        bruce.jvminjava.rtda.heap.Class klass = field.getKlass();
        if (field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }
        
        if (field.isFinal()) {
            if (currentClass != klass || currentMethod.getName() != "<clinit>") {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
        }
        
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();
        
        switch(descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
            {
                int val = stack.popInt();
                Object ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setInt(slotId, val);
            }
                break;
            case 'F':
            {
                float val = stack.popFloat();
                Object ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setFloat(slotId, val);
            }
                break;
            case 'J':
            {
                long val = stack.popLong();
                Object ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setLong(slotId, val);
            }
                break;
            case 'D':
            {
                double val = stack.popDouble();
                Object ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setDouble(slotId, val);
            }
                break;
            case 'L':
            case '[':
            {
                Object val = stack.popRef();
                Object ref = stack.popRef();
                if (ref == null) {
                    throw new RuntimeException("java.lang.NullPointerException");
                }
                ref.getFields().setRef(slotId, val);
            }
                break;
        }
    }
}
