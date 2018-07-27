package bruce.jvminjava.instructions.extended;

import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;

public class GOTO_W implements Instruction{
    private int offset;
    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        BranchLogic.branch(frame, offset);
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        offset = (int) reader.readInt32();
    }

}
