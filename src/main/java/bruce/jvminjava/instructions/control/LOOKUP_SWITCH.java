package bruce.jvminjava.instructions.control;

import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;

public class LOOKUP_SWITCH implements Instruction{
    private long defaultOffset;
    private long npairs;
    private long[] matchOffsets;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s((int)(npairs*2));
    }

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        int key = frame.getOperandStack().popInt();
        for (long i =0; i<npairs*2; i+=2) {
            if (matchOffsets[(int)i] == key) {
                long offset = matchOffsets[(int)(i+1)];
                BranchLogic.branch(frame, (int)offset);
                return;
            }
        }
        BranchLogic.branch(frame, (int)defaultOffset);
    }

}
