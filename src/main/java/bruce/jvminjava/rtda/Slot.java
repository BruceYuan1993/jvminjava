package bruce.jvminjava.rtda;
import bruce.jvminjava.rtda.heap.Object;


public class Slot {
	private int num;
	private Object ref;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Object getRef() {
		return ref;
	}
	public void setRef(Object ref) {
		this.ref = ref;
	}
	
	
}
