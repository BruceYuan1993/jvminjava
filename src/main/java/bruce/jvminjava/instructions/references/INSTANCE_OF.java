package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ClassReference;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Object;

public class INSTANCE_OF extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
            return;
        }
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        ClassReference classRef = (ClassReference) cp.getConstant(getIndex());
        Class klass = classRef.resolvedClass();
        if (ref.isInstanceOf(klass)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }
}
