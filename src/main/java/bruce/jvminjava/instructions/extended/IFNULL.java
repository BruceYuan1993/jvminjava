package bruce.jvminjava.instructions.extended;

import bruce.jvminjava.instructions.base.BranchInstruction;
import bruce.jvminjava.instructions.base.BranchLogic;
import bruce.jvminjava.rtda.Frame;

public class IFNULL extends BranchInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Object ref = frame.getOperandStack().popRef();
        if (ref != null) {
            BranchLogic.branch(frame, getOffset());
        }
    }

}
