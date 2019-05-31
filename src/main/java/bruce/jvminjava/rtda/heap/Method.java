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
    
    
    void injectCodeAttribute(String returnType) {
        this.maxStack = 4;
        this.maxLocals = argSlotCount;
        
        switch (returnType.charAt(0)) {
            case 'V':
                code = new byte[]{(byte) 0xfe, (byte) 0xb1}; // return
                break;
            case 'L':
            case '[':
                code = new byte[]{(byte) 0xfe, (byte) 0xb0}; // areturn
                break;
            case 'D':
                code = new byte[]{(byte) 0xfe, (byte) 0xaf}; // dreturn
                break;
            case 'F':
                code = new byte[]{(byte) 0xfe, (byte) 0xae}; // freturn
                break;
            case 'J':
                code = new byte[]{(byte) 0xfe, (byte) 0xad}; // lreturn
                break;
            default:
                code = new byte[]{(byte) 0xfe, (byte) 0xac}; // ireturn
                break;
        }
    }
}
