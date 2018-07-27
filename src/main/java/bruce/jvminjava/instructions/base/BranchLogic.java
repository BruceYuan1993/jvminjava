package bruce.jvminjava.instructions.base;

import bruce.jvminjava.rtda.Frame;

public class BranchLogic {
    public static void branch(Frame frame, int offset){
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPC(nextPc);
    }

}
