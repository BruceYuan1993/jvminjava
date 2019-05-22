package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.MethodReference;

public class INVOKE_VIRTUAL extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Method currentMethod = frame.getMethod();
        bruce.jvminjava.rtda.heap.Class currentClass = currentMethod.getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodReference methodRef = (MethodReference) cp.getConstant(getIndex());
        
        if ("println".equals(methodRef.getName())) {
            OperandStack stack = frame.getOperandStack();
            switch (methodRef.getDescriptor()) {
            case "(Z)V":
                System.out.println(stack.popInt() != 0);
                break;
            case "(C)V":
                System.out.println(stack.popInt());
                break;
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.println(stack.popInt());
                break;
            case "(F)V":
                System.out.println(stack.popFloat());
                break;
            case "(J)V":
                System.out.println(stack.popLong());
                break;
            case "(D)V":
                System.out.println(stack.popDouble());
                break;
            default:
                System.out.println(methodRef.getDescriptor());
                break;
            }
            stack.popRef();
        }
        
    }

}
