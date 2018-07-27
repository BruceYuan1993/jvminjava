package bruce.jvminjava.rtda.heap;

public class Method extends ClassMember{
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    
    public int getMaxStack() {
        return maxStack;
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
}
