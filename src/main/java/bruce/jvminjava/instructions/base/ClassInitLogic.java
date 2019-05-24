package bruce.jvminjava.instructions.base;

import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.Thread;
import bruce.jvminjava.rtda.heap.Class;
import bruce.jvminjava.rtda.heap.Method;

public class ClassInitLogic {

    public static void initClass(Thread thread, Class klass) {
        // TODO Auto-generated method stub
        klass.startInit();
        scheduleClient(thread, klass);
        initSuperClass(thread, klass);
    }

    private static void initSuperClass(Thread thread, Class klass) {
        // TODO Auto-generated method stub
        if (!klass.isInterface()) {
            Class superClass = klass.getSuperClass();
            if (superClass != null && !superClass.initStarted()) {
                initClass(thread, superClass);
            }
        }
    }

    private static void scheduleClient(Thread thread, Class klass) {
        // TODO Auto-generated method stub
        Method clinit = klass.getClinitMethod();
        if (clinit != null) {
            Frame frame = thread.newFrame(clinit);
            thread.pushFrame(frame);
        }
    }

}
