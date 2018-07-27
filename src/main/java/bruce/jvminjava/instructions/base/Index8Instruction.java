package bruce.jvminjava.instructions.base;

public abstract class Index8Instruction implements Instruction{
    private short index;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        this.index = reader.readInt8();
    }
    public short getIndex() {
        return index;
    }
    public void setIndex(short index) {
        this.index = index;
    }
}
