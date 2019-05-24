package bruce.jvminjava.instructions.references;


import bruce.jvminjava.instructions.base.BytecodeReader;
import bruce.jvminjava.instructions.base.Instruction;
import bruce.jvminjava.instructions.base.MethodInvokeLogic;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.InterfaceMethodReference;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.Object;

public class INVOKE_INTERFACE implements Instruction{
    private int index;
    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        Class currentClass = frame.getMethod().getKlass();
        ConstantPool cp = currentClass.getConstantPool();
        InterfaceMethodReference methodRef = (InterfaceMethodReference) cp.getConstant(index); 
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new RuntimeException("Incompatible class change");
        }
        
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);
        if (ref == null) {
            throw new RuntimeException("Null pointer exception");
        }
        if (!ref.getKlass().isImplements(methodRef.resolvedClass())) {
            throw new RuntimeException("Incompatible class change");
        }
        
        Method methodToBeInvoked =  MethodInvokeLogic.lookupMethodInClass(ref.getKlass(), methodRef.getName(),
                methodRef.getDescriptor());
        
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new RuntimeException("Abstract Method exception");
        }
        
        if (!methodToBeInvoked.isPublic()) {
            throw new RuntimeException("IllegalAccessError");
        }
        
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // TODO Auto-generated method stub
        index = reader.readInt16();
        reader.readInt8();
        reader.readInt8();
    }

}
