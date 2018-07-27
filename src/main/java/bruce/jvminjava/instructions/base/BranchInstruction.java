package bruce.jvminjava.instructions.base;

public abstract class BranchInstruction implements Instruction{
    private int offset;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        this.offset = reader.readInt16();
        System.out.println("GOTO offset --> " + this.offset);
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
