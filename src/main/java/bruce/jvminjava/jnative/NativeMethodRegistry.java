package bruce.jvminjava.jnative;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import bruce.jvminjava.rtda.Frame;

public enum NativeMethodRegistry {
    INSTANCE;
    static {
        scan();
    }
    private Map<String, NativeMethod> registry = new HashMap<>();
    private NativeMethod emptyNativeMethod = new EmptyNativeMethod();
    
    public void register(String className, String methodName, String methodDescriptor, NativeMethod method) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        registry.put(key, method);
    }
    
    public NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        if (registry.containsKey(key)) {
            return registry.get(key);
        }
        
        if ("()V".equals(methodDescriptor) && "registerNatives".equals(methodName)) {
            return emptyNativeMethod;
        }
        
        return null;
    }
    
    private static class EmptyNativeMethod extends NativeMethod{
        public EmptyNativeMethod() {
            super(null, null, null);
        }

        @Override
        public void invoke(Frame frame) {
            
        }
        
    }
    
    public static void scan() {
        Reflections reflections = new Reflections(NativeMethod.class.getPackage().getName());
        Set<Class<? extends NativeMethod>> nativeMethodClasses = 
            reflections.getSubTypesOf(NativeMethod.class);
        
        for(Class<? extends NativeMethod> c : nativeMethodClasses) {
            if (Modifier.isAbstract(c.getClass().getModifiers())) {
                continue;
            }
            
            try {
                NativeMethod nm = c.newInstance();
                NativeMethodRegistry.INSTANCE.register(nm.getRegisterClassName(),
                        nm.getRegisterMethodName(), 
                        nm.getRegisterMethodDescriptor(),
                        nm);
            } catch (Exception e) {
                throw new RuntimeException("Register native method failed: " + e.getMessage());
            }
        }
    }
}
