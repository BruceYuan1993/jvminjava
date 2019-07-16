package bruce.jvminjava.jnative.java.lang.object;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Object;

public class HashCode extends NativeMethod{

    public HashCode() {
        super("java/lang/Object", "hashCode", "()I");
    }

    @Override
    public void invoke(Frame frame) {
//        Object thisObj = frame.getLocalVars().getThis();
//        // System.identityHashCode(thisObj);
//        Unsafe.getUnsafe().getAddress(0);
        //frame.getOperandStack().pushInt(hash);
        Integer i = Integer.valueOf(1);
    }

}
