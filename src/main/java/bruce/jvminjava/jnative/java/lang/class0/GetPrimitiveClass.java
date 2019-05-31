package bruce.jvminjava.jnative.java.lang.class0;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Object;
import bruce.jvminjava.rtda.heap.StringPool;
import bruce.jvminjava.rtda.heap.ClassLoader;

public class GetPrimitiveClass extends NativeMethod{

    public GetPrimitiveClass() {
        super("java/lang/Class", "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;");
    }

    @Override
    public void invoke(Frame frame) {
        // TODO Auto-generated method stub
       Object nameObj = frame.getLocalVars().getRef(0);
       String name = StringPool.INSTANCE.originString(nameObj);
       
       ClassLoader loader = frame.getMethod().getKlass().getLoader();
       Object  classObj = loader.loadClass(name).getJClass();
       
       frame.getOperandStack().pushRef(classObj);
    }
}
