package bruce.jvminjava.rtda.heap;

public class Object {
    Class klass;
    Slots fields;
    public Class getKlass() {
        return klass;
    }
    public void setKlass(Class klass) {
        this.klass = klass;
    }

    public Slots getFields() {
        return fields;
    }
    public void setFields(Slots fields) {
        this.fields = fields;
    }
    public Object(Class klass) {
        this.klass = klass;
        this.fields = new Slots(klass.getInstanceSlotCount());
    } 
    
    
    public boolean isInstanceOf(Class klass) {
        return klass.isAssignableFrom(this.klass);
    }
}
