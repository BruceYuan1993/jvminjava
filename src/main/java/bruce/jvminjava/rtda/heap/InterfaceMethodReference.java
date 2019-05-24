package bruce.jvminjava.rtda.heap;

import java.util.List;

public class InterfaceMethodReference extends MemberReference{
    private Method method;

    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    
    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            resolveInterfaceMethodRef();
        }
        return this.method;
    }
    private void resolveInterfaceMethodRef() {
        // TODO Auto-generated method stub
        Class d = this.getConstantPool().getKlass();
        Class c = this.resolvedClass();
        
        if (!c.isInterface()) {
            throw new RuntimeException("IncompatibleClassChangeError");
        }
        
        Method method = lookupInterfaceMethod(c, getName(), getDescriptor());
        
        if (method == null) {
            throw new RuntimeException("NoSuchMethoderror");
        }
        
        if(!method.isAccessibleTo(d)) {
            throw new RuntimeException("IllegalAccessError");
        }
        this.method = method;
    }
    private Method lookupInterfaceMethod(Class c, String name, String descriptor) {
        // TODO Auto-generated method stub
        for (Method method : c.getMethods()) {
            if (name.equals(method.getName()) && descriptor.equals(method.getDescriptor())) {
                return method;
            }
        }
        return lookupMethodInInterfaces(c.getInterfaces(), name, descriptor);
        
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
}
