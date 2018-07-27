package bruce.jvminjava.instructions.comparisons;

import bruce.jvminjava.instructions.base.BranchInstruction;
import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.rtda.Frame;

public class IFLT extends BranchInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        int val = frame.getOperandStack().popInt();
        if (val < 0) {
            BranchLogic.branch(frame, getOffset());
        }
    }

}
