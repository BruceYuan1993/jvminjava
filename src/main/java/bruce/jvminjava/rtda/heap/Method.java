package bruce.jvminjava.rtda.heap;
import static bruce.jvminjava.rtda.heap.AccessFlags.ACC_ABSTRACT;
import static bruce.jvminjava.rtda.heap.AccessFlags.ACC_NATIVE;
public class Method extends ClassMember{
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private int argSlotCount;
    
    public int getMaxStack() {
        return maxStack;
    }
    public int getArgSlotCount() {
        return argSlotCount;
    }
    public void setArgSlotCount(int argSlotCount) {
        this.argSlotCount = argSlotCount;
    }
    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }
    public int getMaxLocals() {
        return maxLocals;
    }
    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }
    public byte[] getCode() {
        return code;
    }
    public void setCode(byte[] code) {
        this.code = code;
    }
    
    public boolean isAbstract() {
        return 0 != (getAccessFlags() & ACC_ABSTRACT);
    }
    public boolean isNative() {
        // TODO Auto-generated method stub
        return 0 != (getAccessFlags() & ACC_NATIVE);
    }
}
