package bruce.jvminjava.instructions.base;

public abstract class Index16Instruction implements Instruction{
    private int index;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        this.index = reader.readInt16();
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
