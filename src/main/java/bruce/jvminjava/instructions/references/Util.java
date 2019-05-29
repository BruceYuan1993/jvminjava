package bruce.jvminjava.instructions.references;

import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.StringPool;

public class Util {
    public static void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        Class klass = frame.getMethod().getKlass();
        Object c = cp.getConstant(index);
        
        if (c.getClass() == Long.class) {
            stack.pushLong((long)c);
        } else if (c.getClass() == Double.class) {
            stack.pushDouble((double)c);
        } else if (c.getClass() == Integer.class) {
            stack.pushInt((int)c);
        } else if (c.getClass() == Float.class) {
            stack.pushFloat((float)c);
        } else if (c.getClass() == String.class) {
            String originStr = (String)c;
            bruce.jvminjava.rtda.heap.Object str = StringPool.INSTANCE.jString(klass.getLoader(), originStr);
            stack.pushRef(str);
        } else {
            throw new RuntimeException("Unsupport Type");
        }
    }
}
