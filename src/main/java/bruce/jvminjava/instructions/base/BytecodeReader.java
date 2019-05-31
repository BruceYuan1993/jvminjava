package bruce.jvminjava.instructions.base;

public class BytecodeReader {

    private byte[] code;
    private int pc = 0;
    
    public BytecodeReader(byte[] codes) {
        // TODO Auto-generated constructor stub
        this.code = codes;
    }
    public BytecodeReader() {
    }

    public int getPc() {
        return pc;
    }

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }
    
    public short readInt8() {
        return (short) Byte.toUnsignedInt(code[pc++]);
    }
    
    public int readInt16() {
        byte byte1 = code[pc++];
        byte byte2 = code[pc++];
        
        int u1 = byte1 & 0xff;
        int u2 = byte2 & 0xff;
        return (u1 << 8)|u2;
    }
    
    public long readInt32() {
        byte byte1 = code[pc++];
        byte byte2 = code[pc++];
        byte byte3 = code[pc++];
        byte byte4 = code[pc++];
        return (byte1 << 24)|(byte2 << 16)|(byte3 << 8)|byte4;
    }
    
    public long[] readInt32s(int length) {
        long[] result = new long[length];
        int i = 0;
        while (i++ < length) {
            result[i] = readInt32();
        }
        return result;
    }
    
    public void skipPadding() {
        while (this.pc % 4 != 0) {
            readInt8();
        }
    }
}
