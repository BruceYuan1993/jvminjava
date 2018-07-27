package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import static bruce.jvminjava.instructions.loads.ILOADBase.*;

public class ILOAD_3 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        iload(frame, 3);
    }

}
