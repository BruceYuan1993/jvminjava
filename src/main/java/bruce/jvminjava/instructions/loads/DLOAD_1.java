package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import static bruce.jvminjava.instructions.loads.DLOADBase.*;

public class DLOAD_1 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        load(frame, 1);
    }

}
