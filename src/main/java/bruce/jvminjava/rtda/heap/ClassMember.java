package bruce.jvminjava.rtda.heap;

import static bruce.jvminjava.rtda.heap.AccessFlags.*;

public class ClassMember {
    private int accessFlags;
    private String name;
    private String descriptor;
    private Class klass;
    public int getAccessFlags() {
        return accessFlags;
    }
    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescriptor() {
        return descriptor;
    }
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
    public Class getKlass() {
        return klass;
    }
    public void setKlass(Class klass) {
        this.klass = klass;
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & ACC_PUBLIC);
    }
    
    public boolean isPrivate() {
        return 0 != (this.accessFlags & ACC_PRIVATE);
    }
    
    public boolean isProtected() {
        return 0 != (this.accessFlags & ACC_PROTECTED);
    }
    
    public boolean isFinal() {
        return 0 != (this.accessFlags & ACC_FINAL);
    }
    
    public boolean isStatic() {
        return 0 != (this.accessFlags & ACC_STATIC);
    }
    
    public boolean isSynthetic() {
        return 0 != (this.accessFlags & ACC_SYNTHETIC);
    }
    
    boolean isAccessibleTo(Class d) {
        if (isPublic()) {
            return true;
        }
        Class c = this.getKlass();
        if (isProtected()) {
            return d == c || d.isSubClassOf(c) || c.getPackageName().equals(d.getPackageName());
        }
        if (!isPrivate()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c;
    }
}
