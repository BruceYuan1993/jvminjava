package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.ClassInitLogic;
import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.instructions.base.MethodInvokeLogic;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Method;
import bruce.jvminjava.rtda.heap.MethodReference;

public class INVOKE_STATIC extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        // TODO Auto-generated method stub
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        MethodReference methodRef = (MethodReference) cp.getConstant(getIndex()); 
        Method method = methodRef.resolvedMethod();
        if (!method.isStatic()) {
            throw new RuntimeException("Incompatible class change");
        }
        Class klass = method.getKlass();
        if (!klass.initStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.getThread(), klass);
            return;
        }
        MethodInvokeLogic.invokeMethod(frame, method);
    }

}
