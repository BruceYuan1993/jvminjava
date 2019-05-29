package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.instructions.base.MethodInvokeLogic;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.OperandStack;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.MethodReference;
import bruce.jvminjava.rtda.heap.Object;
import bruce.jvminjava.rtda.heap.StringPool;

public class INVOKE_VIRTUAL extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Class currentClass = frame.getMethod().getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodReference methodRef = (MethodReference) cp.getConstant(getIndex()); 
        Method resolvedMethod = methodRef.resolvedMethod();
        
        if (resolvedMethod.isStatic()) {
            throw new RuntimeException("Incompatible class change");
        }
        
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        if (ref == null) {
            if ("println".equals(methodRef.getName())) {
               _print(frame.getOperandStack(), methodRef.getDescriptor());
               return;
           }
            throw new RuntimeException("Null pointer exception");
        }
        
        if (resolvedMethod.isProtected() && resolvedMethod.getKlass().isSuperClassOf(currentClass)
                && !resolvedMethod.getKlass().getPackageName().equals(currentClass.getPackageName())
                && ref.getKlass() != currentClass && ref.getKlass().isSubClassOf(currentClass)) {
            throw new RuntimeException("IllegalAccessError");
        }
        
        Method methodToBeInvoked =  MethodInvokeLogic.lookupMethodInClass(currentClass, methodRef.getName(),
                methodRef.getDescriptor());
        
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new RuntimeException("Abstract Method exception");
        }
        
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
    
    
    private void _print(OperandStack stack, String descriptor) {
        switch (descriptor) {
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
        case "(Ljava/lang/String;)V":
            Object jStr = stack.popRef();
            String str = StringPool.INSTANCE.originString(jStr);
            System.out.println(str);
            break;
        default:
            System.out.println(descriptor);
            break;
        }
        stack.popRef();
    }

}
