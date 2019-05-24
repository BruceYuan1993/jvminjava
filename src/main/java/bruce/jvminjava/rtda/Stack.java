package bruce.jvminjava.rtda;

public class Stack {
	private int maxSize;
	private int size;
	private Frame top;
	
	public Stack(int maxSize) {
		this.maxSize = maxSize;
	}

	public void push(Frame item){
		if (size >= maxSize){
			throw new RuntimeException("Stack overflow");
		}
		
		if (top != null){
			item.setLower(top);
		}
		
		top = item;
		size++;
	}
	
	public int getSize() {
		return size;
	}

	public Frame pop(){
		if (top == null){
			throw new RuntimeException("jvm stack is empty");
		}
		Frame result = top;
		top = top.getLower();
		result.setLower(null);
		size--;
		return result;
	}
	
	public Frame top(){
		if (top == null){
			throw new RuntimeException("jvm stack is empty");
		}
		
		return top;
	}

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return top == null;
    }
}
