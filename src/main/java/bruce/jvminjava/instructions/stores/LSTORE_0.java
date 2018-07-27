package bruce.jvminjava.instructions.stores;

import bruce.jvminjava.instructions.base.NoOperandsInstruction;
import bruce.jvminjava.rtda.Frame;
import static bruce.jvminjava.instructions.stores.LSTOREBase.*;

public class LSTORE_0 extends NoOperandsInstruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        store(frame, 0);
    }
}
