package bruce.jvminjava.instructions.control;

import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;

public class TABLE_SWITCH implements Instruction{

    private long defaultOffset;
    private long low;
    private long high;
    private long[] jumpOffsets;
    
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
        int count = (int) (high - low - 1);
        jumpOffsets = reader.readInt32s(count);
    }

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        int index = frame.getOperandStack().popInt();
        int offset;
        
        if (index >= low && index <= high) {
            offset = (int) jumpOffsets[(int) (index - low)];
        } else {
            offset = (int) defaultOffset;
        }
        BranchLogic.branch(frame, offset);
    }

}
