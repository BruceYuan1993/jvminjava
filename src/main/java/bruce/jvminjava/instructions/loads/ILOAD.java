package bruce.jvminjava.instructions.loads;

import bruce.jvminjava.instructions.base.Index8Instruction;
import bruce.jvminjava.rtda.Frame;
import static bruce.jvminjava.instructions.loads.ILOADBase.*;

public class ILOAD extends Index8Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        iload(frame, this.getIndex());
    }

}
