package bruce.jvminjava.jnative.java.lang.object;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;

public class Clone extends NativeMethod{

    public Clone() {
        super("java/lang/Object", "clone", "()Ljava/lang/Object;");
    }

    @Override
    public void invoke(Frame frame) {
        // TODO Auto-generated method stub
        
    }

}
