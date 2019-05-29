package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index8Instruction;
import bruce.jvminjava.rtda.Frame;

public class LDC extends Index8Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Util._ldc(frame, getIndex());
    }
    
}
