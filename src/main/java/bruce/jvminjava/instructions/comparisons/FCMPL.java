package bruce.jvminjava.instructions.comparisons;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;

public class FCMPL extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        FCMPBase.compare(frame, false);
    }

}
