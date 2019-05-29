package bruce.jvminjava.rtda.heap;


public class Object {
    protected Class klass;
    protected java.lang.Object data;
    
    public Object(Class klass, java.lang.Object data) {
        this.klass = klass;
        this.data = data;
    }

    public Class getKlass() {
        return klass;
    }
    public void setKlass(Class klass) {
        this.klass = klass;
    }

    public Slots getFields() {
        return (Slots)data;
    }
    public void setFields(Slots fields) {
        this.data = fields;
    }
    
    public boolean isInstanceOf(Class klass) {
        return klass.isAssignableFrom(this.klass);
    }
}
