package bruce.jvminjava.rtda.heap;

import static bruce.jvminjava.rtda.heap.AccessFlags.*;

public class Field extends ClassMember{
    private int slotId;
    private int constValueIndex = -1;
    public boolean isVolatile() {
        return 0 != (this.getAccessFlags() & ACC_VOLATILE);
    }
    
    public boolean isTransient() {
        return 0 != (this.getAccessFlags() & ACC_TRANSIENT);
    }
    
    public boolean isEnum() {
        return 0 != (this.getAccessFlags() & ACC_ENUM);
    }
    
    public boolean isLongOrDouble() {
        return this.getDescriptor().equals("J")||this.getDescriptor().equals("D");
    }
    
    public int getSlotId() {
        return slotId;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

}
