package bruce.jvminjava.jnative.java.lang.float0;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;

public class FloatToRawIntBits extends NativeMethod{

    public FloatToRawIntBits() {
        super("java/lang/Float", "floatToRawIntBits", "(F)I");
    }

    @Override
    public void invoke(Frame frame) {
       float value = frame.getLocalVars().getFloat(0);
       int intBits = Float.floatToRawIntBits(value);
       frame.getOperandStack().pushInt(intBits);
    }
}
