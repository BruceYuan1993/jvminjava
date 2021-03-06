package bruce.jvminjava.instructions.references;

import bruce.jvminjava.instructions.base.ClassInitLogic;
import bruce.jvminjava.instructions.base.Index16Instruction;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.heap.ClassReference;
import bruce.jvminjava.rtda.heap.ConstantPool;
import bruce.jvminjava.rtda.heap.Object;

public class NEW extends Index16Instruction{

    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getKlass().getConstantPool();
        ClassReference classRef = (ClassReference)cp.getConstant(getIndex());
        bruce.jvminjava.rtda.heap.Class klass = classRef.resolvedClass();
        
        if (!klass.initStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.getThread(), klass);
            return;
        }
        
        if (klass.isInterface() || klass.isAbstract()) {
            throw new RuntimeException("Instantiation Error");
        }
        
        Object obj = klass.newObject();
        frame.getOperandStack().pushRef(obj);
    }

}
