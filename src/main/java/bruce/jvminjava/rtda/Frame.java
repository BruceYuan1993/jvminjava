package bruce.jvminjava.rtda;

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
	private int nextPC;
	
	public Frame(Thread thread, int maxLocals, int maxStack) {
        super();
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    private OperandStack operandStack;
	
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
