package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.instructions.base.MethodInvokeLogic;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.MethodReference;
import bruce.jvminjava.rtda.heap.Object;

public class INVOKE_SPECIAL extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Class currentClass = frame.getMethod().getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodReference methodRef = (MethodReference) cp.getConstant(getIndex()); 
        Class resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();
        
        if ("<init>".equals(resolvedMethod.getName()) && resolvedMethod.getKlass() != resolvedClass) {
            throw new RuntimeException("No such method error");
        }
        if (resolvedMethod.isStatic()) {
            throw new RuntimeException("Incompatible class change");
        }
        
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new RuntimeException("Null pointer exception");
        }
        
        if (resolvedMethod.isProtected() && resolvedMethod.getKlass().isSuperClassOf(currentClass)
                && !resolvedMethod.getKlass().getPackageName().equals(currentClass.getPackageName())
                && ref.getKlass() != currentClass && ref.getKlass().isSubClassOf(currentClass)) {
            throw new RuntimeException("IllegalAccessError");
        }
        
        Method methodToBeInvoked = resolvedMethod;
        if (currentClass.isSuper() && resolvedClass.isSuperClassOf(currentClass)
                && !"<init>".equals(resolvedMethod.getName())) {
            methodToBeInvoked =  MethodInvokeLogic.lookupMethodInClass(currentClass, methodRef.getName(),
                    methodRef.getDescriptor());
        }
        
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new RuntimeException("Abstract Method exception");
        }
        
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

}
