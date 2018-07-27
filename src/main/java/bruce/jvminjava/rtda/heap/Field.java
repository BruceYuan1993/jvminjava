package bruce.jvminjava.rtda.heap;

import static bruce.jvminjava.rtda.heap.AccessFlags.*;

public class Field extends ClassMember{
    public boolean isVolatile() {
        return 0 != (this.getAccessFlags() & ACC_VOLATILE);
    }
    
    public boolean isTransient() {
        return 0 != (this.getAccessFlags() & ACC_TRANSIENT);
    }
    
    public boolean isEnum() {
        return 0 != (this.getAccessFlags() & ACC_ENUM);
    }
}
