package bruce.jvminjava.instructions;

import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.Thread;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ClassLoader;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.Object;
import bruce.jvminjava.rtda.heap.StringPool;

public class Interpreter {
    public void interpret(Method method, String[] args) {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        
        
        Array argsArr = createArgsArray(method.getKlass().getLoader(), args);
        frame.getLocalVars().setRef(0, argsArr);
        
        try {
            loop(thread);
        } catch (Exception e) {
            logFrames(thread);
            e.printStackTrace();
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
//            logInstruction(frame, inst);
            
//            int index = 0;
//            if (inst instanceof Index16Instruction) {
//                index = ((Index16Instruction) inst).getIndex();
//            }
//            System.out.printf("stack:%2d\n", frame.getOperandStack().getSize());
//            System.out.printf("pc:%2d inst:%s", pc, inst.getClass().getSimpleName() + " {{" + index + "}}\n");
//            
            
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
    
//    private void logInstruction(Frame frame, Instruction inst) {
//        Method method = frame.getMethod();
//        String className = method.getKlass().getName();
//        String methodName = method.getName();
//        int pc = frame.getThread().getPc() ;
//        
//        System.out.println(className + "." + methodName + "() # " + pc +" " 
//                + inst.getClass().getSimpleName());
//    }
    
    private Array createArgsArray(ClassLoader loader, String[] args) {
        Class strClass = loader.loadClass("java/lang/String");
        Array argsArr = strClass.getArrayClass().newArray(args.length);
        Object[] jArgs = argsArr.getRefs();
        int i = 0;
        for (String s : args) {
            jArgs[i] = StringPool.INSTANCE.jString(loader, s);
            i++;
        }
        return argsArr;
    }
}
