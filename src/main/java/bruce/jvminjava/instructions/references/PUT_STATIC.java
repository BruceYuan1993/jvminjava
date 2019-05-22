package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Field;
import bruce.jvminjava.rtda.heap.FieldReference;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.Slots;

public class PUT_STATIC extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Method currentMethod = frame.getMethod();
        bruce.jvminjava.rtda.heap.Class currentClass = currentMethod.getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        FieldReference fieldRef = (FieldReference) cp.getConstant(getIndex());
        Field field = fieldRef.resolvedField();
        bruce.jvminjava.rtda.heap.Class klass = field.getKlass();
        if (!field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }
        
        if (field.isFinal()) {
            if (currentClass != klass || currentMethod.getName() != "<clinit>") {
                throw new RuntimeException("java.lang.IllegalAccessError");
            }
        }
        
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = klass.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        
        switch(descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.setInt(slotId, stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L':
            case '[':
                slots.setRef(slotId, stack.popRef());
                break;
        }
    }
}
