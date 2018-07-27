package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import static bruce.jvminjava.instructions.loads.LLOADBase.*;

public class LLOAD_0 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        load(frame, 0);
    }

}
