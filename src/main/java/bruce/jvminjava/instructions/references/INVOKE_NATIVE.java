package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.jnative.NativeMethodRegistry;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Method;

public class INVOKE_NATIVE extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        String className = method.getKlass().getName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();
        
        NativeMethod nm = NativeMethodRegistry.INSTANCE
                .findNativeMethod(className, methodName, methodDescriptor);
        if (nm == null) {
            String methodInfo = className + "." + methodName + "." + methodDescriptor;
            throw new RuntimeException("UnsatisfiedLinkError: " + methodInfo);
        }
        
        nm.invoke(frame);
    }

}
