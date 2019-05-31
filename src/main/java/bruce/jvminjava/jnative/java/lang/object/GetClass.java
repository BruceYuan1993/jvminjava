package bruce.jvminjava.jnative.java.lang.object;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Object;

public class GetClass extends NativeMethod {

    public GetClass() {
        super("java/lang/Object", "getClass", "()Ljava/lang/Class;");
    }

    @Override
    public void invoke(Frame frame) {
        Object jthis = frame.getLocalVars().getThis();
        Object jClassObj = jthis.getKlass().getJClass();
        frame.getOperandStack().pushRef(jClassObj);
    }
}
