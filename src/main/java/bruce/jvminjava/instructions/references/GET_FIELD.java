package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Field;
import bruce.jvminjava.rtda.heap.FieldReference;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.Object;
import bruce.jvminjava.rtda.heap.Slots;

public class GET_FIELD extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Method currentMethod = frame.getMethod();
        bruce.jvminjava.rtda.heap.Class currentClass = currentMethod.getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        FieldReference fieldRef = (FieldReference) cp.getConstant(getIndex());
        Field field = fieldRef.resolvedField();
        if (field.isStatic()) {
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        }
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        
        if (ref == null) {
            throw new RuntimeException("java.lang.NullPointerException");
        }
        
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = ref.getFields();
        
        switch(descriptor.charAt(0)) {
        case 'Z':
        case 'B':
        case 'C':
        case 'S':
        case 'I':
            stack.pushInt(slots.getInt(slotId));
            break;
        case 'F':
            stack.pushFloat(slots.getFloat(slotId));
            break;
        case 'J':
            stack.pushLong(slots.getLong(slotId));
            break;
        case 'D':
            stack.pushDouble(slots.getDouble(slotId));
            break;
        case 'L':
        case '[':
            stack.pushRef(slots.getRef(slotId));
            break;
    }
    }

}
