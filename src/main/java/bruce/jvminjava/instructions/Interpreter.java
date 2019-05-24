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
            loop(thread);
        } catch (Exception e) {
            e.printStackTrace();
            logFrames(thread);
        }
    }
    
    
    private void loop(Thread thread) {
       
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPC();
            thread.setPc(pc);
            
            reader.reset(frame.getMethod().getCode(), pc);
            short opCode = reader.readInt8();
            
            Instruction inst = InstructionFactory.newInstruction(opCode);
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            //logInstruction(frame, inst);
            
//            System.out.printf("stack:%2d\n", frame.getOperandStack().getSize());
//            System.out.printf("pc:%2d inst:%s\n", pc, inst.getClass().getSimpleName());
            
            inst.execute(frame);
            
            if (thread.isStackEmpty()) {
                break;
            }
            
        }
    }
    
    private void logFrames(Thread thread) {
        while (!thread.isStackEmpty()) {
            Frame frame = thread.popFrame();
            Method method = frame.getMethod();
            String className = method.getKlass().getName();
            System.out.println(">> pc:" + frame.getNextPC() + " " + className + "." + method.getName()
                + method.getDescriptor());
        }
    }
    
    private void logInstruction(Frame frame, Instruction inst) {
        Method method = frame.getMethod();
        String className = method.getKlass().getName();
        String methodName = method.getName();
        int pc = frame.getThread().getPc() ;
        
        System.out.println(className + "." + methodName + "() # " + pc +" " 
                + inst.getClass().getSimpleName());
    }
}
