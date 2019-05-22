package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;

public class LDC2_W extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        _ldc(frame, getIndex());
    }
    
    void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        Object c = cp.getConstant(getIndex());
        
        if (c.getClass() == Long.class) {
            stack.pushLong((long)c);
        } else if (c.getClass() == Double.class) {
            stack.pushDouble((double)c);
        } else {
            
        }
    }

}
