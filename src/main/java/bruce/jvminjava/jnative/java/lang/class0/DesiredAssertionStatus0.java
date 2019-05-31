package bruce.jvminjava.jnative.java.lang.class0;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;

public class DesiredAssertionStatus0 extends NativeMethod{
    public DesiredAssertionStatus0() {
        super("java/lang/Class", "desiredAssertionStatus0", "(Ljava/lang/Class;)Z");
    }

    @Override
    public void invoke(Frame frame) {
        frame.getOperandStack().pushBoolean(false);
    }
}
