package bruce.jvminjava.rtda;


public class Thread {
	private int pc;
	private Stack frames;
	
	public Thread() {
		frames = new Stack(1024);
	}
	
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	
	public void pushFrame(Frame frame){
		this.frames.push(frame);
	}
	
	public Frame popFrame(){
		return this.frames.pop();
	}
	
	public Frame currentFrame() {
		return this.frames.top();
	}
	
	
	public Frame newFrame(int maxLocals, int maxStack) {
        return new Frame(this, maxLocals, maxStack);
	}
	
}
