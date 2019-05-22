package bruce.jvminjava.instructions;

import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.Thread;
import bruce.jvminjava.rtda.heap.Method;

public class Interpreter {
    public void interpret(Method method) {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        
        
        try {
            loop(thread, method.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void loop(Thread thread, byte[] codes) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            int pc = frame.getNextPC();
            thread.setPc(pc);
            
            reader.reset(codes, pc);
            short opCode = reader.readInt8();
            
            Instruction inst = InstructionFactory.newInstruction(opCode);
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            System.out.printf("stack:%2d\n", frame.getOperandStack().getSize());
            System.out.printf("pc:%2d inst:%s\n", pc, inst.getClass().getSimpleName());
            inst.execute(frame);
            
        }
    }
}
