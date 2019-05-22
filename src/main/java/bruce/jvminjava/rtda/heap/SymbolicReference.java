package bruce.jvminjava.rtda.heap;

public class SymbolicReference {
    private ConstantPool constantPool;
    private Class klass;
    private String ClassName;
    public ConstantPool getConstantPool() {
        return constantPool;
    }
    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
    public Class getKlass() {
        return klass;
    }
    public void setKlass(Class klass) {
        this.klass = klass;
    }
    public String getClassName() {
        return ClassName;
    }
    public void setClassName(String className) {
        ClassName = className;
    }
    
    public Class resolvedClass() {
        if (this.getKlass() == null) {
            resolveClassRef();
        }
        return this.getKlass();
    }

    private void resolveClassRef() {
        Class d = this.getConstantPool().getKlass();
        Class c = d.getLoader().loadClass(getClassName());
        if (!c.isAccessibleTo(d)) {
            throw new RuntimeException("Illegal access error");
        }
        
        setKlass(c);
    }
}
