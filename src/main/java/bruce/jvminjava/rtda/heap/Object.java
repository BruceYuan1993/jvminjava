package bruce.jvminjava.rtda.heap;


public class Object {
    protected Class klass;
    protected java.lang.Object data;
    private java.lang.Object extra;

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

    public void setRefVar(String name, String descriptor, Object ref) {
        Field f = this.getKlass().getField(name, descriptor, false);
        Slots slots = (Slots) this.data; 
        slots.setRef(f.getSlotId(), ref);
    }
    
    public Object getRefVar(String name, String descriptor) {
        Field f = this.getKlass().getField(name, descriptor, false);
        Slots slots = (Slots) this.data; 
        return slots.getRef(f.getSlotId());
    }
    
    public java.lang.Object getExtra() {
        return extra;
    }

    public void setExtra(java.lang.Object extra) {
        this.extra = extra;
    }
}
