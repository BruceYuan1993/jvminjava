package bruce.jvminjava.rtda;

import bruce.jvminjava.rtda.heap.Method;

public class Frame {
	public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    private Frame lower;
	private LocalVars localVars;
	private Thread thread;
	private OperandStack operandStack;
	private int nextPC;
	private Method method;
	
	public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Frame(Thread thread, Method method) {
        super();
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.getMaxLocals());
        operandStack = new OperandStack(method.getMaxStack());
    }

    
	
	public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars = localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(OperandStack operandStack) {
        this.operandStack = operandStack;
    }

    public Frame getLower() {
		return lower;
	}

	public void setLower(Frame lower) {
		this.lower = lower;
	}
	
	
}
