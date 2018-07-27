package bruce.jvminjava.instructions.base;

import bruce.jvminjava.rtda.Frame;

public interface Instruction {
    void fetchOperands(BytecodeReader reader);
    void execute(Frame frame);
}
