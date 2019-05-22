package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;

public class LDC_W extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        _ldc(frame, getIndex());
    }
    
    void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        Object c = cp.getConstant(getIndex());
        
        if (c.getClass() == Integer.class) {
            stack.pushInt((int)c);
        } else if (c.getClass() == Float.class) {
            stack.pushFloat((float)c);
        } else {
            
        }
    }

}
