package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.instructions.base.Index8Instruction;
import bruce.jvminjava.rtda.Frame;
import static bruce.jvminjava.instructions.stores.LSTOREBase.*;

public class LSTORE extends Index8Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        store(frame, this.getIndex());
    }
}
