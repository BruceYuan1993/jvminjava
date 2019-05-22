package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index8Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;

public class LDC extends Index8Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        _ldc(frame, getIndex());
    }
    
    void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        int a = getIndex();
        Object c = cp.getConstant(a);
        
        if (c.getClass() == Integer.class) {
            stack.pushInt((int)c);
        } else if (c.getClass() == Float.class) {
            stack.pushFloat((float)c);
        } else {
            
        }
    }

}
