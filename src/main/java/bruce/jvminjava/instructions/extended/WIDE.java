package bruce.jvminjava.instructions.extended;

import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;

public class WIDE implements Instruction{

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        short opcode = reader.readInt8();
        switch (opcode) {
        
        }
    }

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        
    }

}
