package bruce.jvminjava.instructions.comparisons;

import bruce.jvminjava.instructions.base.BranchInstruction;
import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;

public class IF_ICMPLE extends BranchInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if (v1 <= v2) {
            BranchLogic.branch(frame, getOffset());
        }
    }

}
