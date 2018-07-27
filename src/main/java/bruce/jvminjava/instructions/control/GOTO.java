package bruce.jvminjava.instructions.control;

import bruce.jvminjava.instructions.base.BranchInstruction;
import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.rtda.Frame;

public class GOTO extends BranchInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        BranchLogic.branch(frame, getOffset());
    }

}
