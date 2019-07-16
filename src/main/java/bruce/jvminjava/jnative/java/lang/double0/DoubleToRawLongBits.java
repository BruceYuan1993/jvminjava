package bruce.jvminjava.jnative.java.lang.double0;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;

public class DoubleToRawLongBits extends NativeMethod{

    public DoubleToRawLongBits() {
        super("java/lang/Double", "doubleToRawLongBits", "(D)J");
    }

    @Override
    public void invoke(Frame frame) {
        // TODO Auto-generated method stub
        Double value = frame.getLocalVars().getDouble(0);
        frame.getOperandStack().pushLong(Double.doubleToRawLongBits(value));
    }

}
