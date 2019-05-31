package bruce.jvminjava.instructions.base;

import java.util.List;

import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.Slot;
import bruce.jvminjava.rtda.Thread;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.Method;

public class MethodInvokeLogic {
    public static void invokeMethod(Frame invokerFrame, Method method) {
        Thread thread = invokerFrame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);
        
        int argSlotSlot = method.getArgSlotCount();
        if (argSlotSlot > 0) {
            for (int i = argSlotSlot-1 ; i>=0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
        
//        if (method.isNative()) {
//            if ("registerNatives".equals(method.getName())) {
//                thread.popFrame();
//            } else {
//                throw new RuntimeException("Native method");
//            }
//        }
    }
    
    public static Method lookupMethodInClass(Class klass, String name, String descriptor) {
        for (Class c = klass; c != null; c = c.getSuperClass()) {
            for (Method method : c.getMethods()) {
                if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method lookupMethodInInterfaces(List<Class> interfaces, String name, String descriptor) {
        for (Class iface : interfaces) {
            for (Method method : iface.getMethods()) {
                if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                    return method;
                }
            }
            
            Method method  = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }

}
