package bruce.jvminjava.jnative.java.lang.string;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.StringPool;
import bruce.jvminjava.rtda.heap.Object;

public class Intern extends NativeMethod{

    public Intern() {
        super("java/lang/String", "intern", "()Ljava/lang/String;");
    }

    @Override
    public void invoke(Frame frame) {
        Object thisObj = frame.getLocalVars().getThis();
        Object interned = StringPool.INSTANCE.internString(thisObj);
        frame.getOperandStack().pushRef(interned);
    }

}
