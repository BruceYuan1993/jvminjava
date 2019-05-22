package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ClassReference;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Object;

public class CHECK_CAST extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        stack.pushRef(ref);
        
        if (ref == null) {
            return;
        }
        
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        ClassReference classRef = (ClassReference) cp.getConstant(getIndex());
        Class klass = classRef.resolvedClass();
        
        if (!ref.isInstanceOf(klass)) {
            throw new RuntimeException("java.lang.ClassCastException");
        }
    }

}
