package bruce.jvminjava.jnative.java.lang.class0;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Object;
import bruce.jvminjava.rtda.heap.StringPool;
import bruce.jvminjava.rtda.heap.Class;

public class GetName0 extends NativeMethod{

    public GetName0() {
        super("java/lang/Class", "getName0", "()Ljava/lang/String;");
    }

    @Override
    public void invoke(Frame frame) {
        Object thisObj = frame.getLocalVars().getThis();
        Class klass = (Class) thisObj.getExtra();
        String name = klass.getJavaName();
        Object strObj = StringPool.INSTANCE.jString(klass.getLoader(), name);
        frame.getOperandStack().pushRef(strObj);
    }
}
