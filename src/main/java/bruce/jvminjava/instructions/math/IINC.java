package bruce.jvminjava.instructions.math;

import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.LocalVars;

public class IINC implements Instruction{
    private short index;
    private long constVal;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        this.index = reader.readInt8();
        this.constVal = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val = (int) (val + constVal);
        localVars.setInt(index, val);
    }

}
