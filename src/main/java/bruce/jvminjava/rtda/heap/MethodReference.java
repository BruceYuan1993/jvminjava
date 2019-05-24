package bruce.jvminjava.rtda.heap;

import java.util.List;

public class MethodReference extends MemberReference{
    private Method method;

    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    
    public Method resolvedMethod() {
        if (this.method == null) {
            resolveMethodRef();
        }
        return this.method;
    }
    private void resolveMethodRef() {
        // TODO Auto-generated method stub
        Class d = this.getConstantPool().getKlass();
        Class c = this.resolvedClass();
        
        if (c.isInterface()) {
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        
        Method method = lookupMethod(c, getName(), getDescriptor());
        
        if (method == null) {
            throw new RuntimeException("NoSuchMethoderror");
        }
        
        if(!method.isAccessibleTo(d)) {
            throw new RuntimeException("IllegalAccessError");
        }
        this.method = method;
    }
    private Method lookupMethod(Class c, String name, String descriptor) {
        // TODO Auto-generated method stub
        
        Method method = lookupMethodInClass(c, name, descriptor);
        if (method == null) {
            method = lookupMethodInInterfaces(c.getInterfaces(), name, descriptor);
        }
        return method;
    }
    private Method lookupMethodInInterfaces(List<Class> interfaces, String name, String descriptor) {
        // TODO Auto-generated method stub
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
    private Method lookupMethodInClass(Class klass, String name, String descriptor) {
        // TODO Auto-generated method stub
        for (Class c = klass; c != null; c = c.getSuperClass()) {
            for (Method method : c.getMethods()) {
                if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                    return method;
                }
            }
        }
        return null;
    }
}
