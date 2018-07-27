package bruce.jvminjava.instructions;

import java.util.List;

import bruce.jvminjava.classanalyzer.ClassMember;
import bruce.jvminjava.classanalyzer.U1;
import bruce.jvminjava.classanalyzer.attribute.CodeAttribute;
import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.Slot;
import bruce.jvminjava.rtda.Thread;

public class Interpreter {
    public void interpret(ClassMember methodinfo) {
        CodeAttribute attr = ClassFileHelper.getCodeAttribute(methodinfo);
        int maxLocals = (int) attr.getMaxLocals().getValue();
        int maxStack = (int) attr.getMaxStack().getValue();
        List<U1> codeList = attr.getCodes();
        byte[] codes = ClassFileHelper.converCodeListToBytes(codeList);
        
        
        
        Thread thread = new Thread();
        Frame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        
        
        try {
            loop(thread, codes);
        } catch (Exception e) {
            e.printStackTrace();
            for (Slot slot : frame.getLocalVars().vars) {
                System.out.println(slot.getNum());
            }
            System.out.println("<<<<<<--------------------->>>>>>");
            for (Slot slot : frame.getOperandStack().slots) {
                System.out.println(slot.getNum());
            }
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
            System.out.println(opCode + " ---> " + inst.getClass());
            inst.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            inst.execute(frame);
            
            System.out.println("<<<<<<---------local vars------------>>>>>>");
            for (Slot slot : frame.getLocalVars().vars) {
                System.out.println(slot.getNum());
            }
            System.out.println("<<<<<<---------stack------------>>>>>>");
            for (Slot slot : frame.getOperandStack().slots) {
                System.out.println(slot.getNum());
            }
            
            System.out.println("<<<<<<----------end----------->>>>>>");
        }
    }
}
