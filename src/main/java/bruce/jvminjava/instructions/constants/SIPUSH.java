package bruce.jvminjava.instructions.constants;

import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;

public class SIPUSH implements Instruction{
    private int val;
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        val = reader.readInt16();
    }

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        frame.getOperandStack().pushInt(val);
    }

}
