package bruce.jvminjava.rtda;

import bruce.jvminjava.rtda.heap.Object;


public class OperandStack {
	public Slot[] slots;
	int size;
	
	public OperandStack(int size){
		if (size > 0){
			slots = new Slot[size];
			for (int i = 0; i< size; i++){
				slots[i] = new Slot();
			}
		}
	}
	
	
	public void pushInt(int val){
		slots[size].setNum(val);
		size++;
	}
	
	public int popInt(){	
		size--;
		return slots[size].getNum();
		
	}
	
	public void pushFloat(float val){
		slots[size].setNum(Float.floatToIntBits(val));
		size++;
	}
	
	public float popFloat(){
		size--;
		return Float.intBitsToFloat(slots[size].getNum());
	}
	
	public void pushLong(long val){
		slots[size].setNum((int)val);
		slots[size+1].setNum((int)(val>>32));
		size += 2;
	}
	
	public long popLong(){	 
	    size -= 2;
		int num1 = slots[size].getNum();
		int num2 = slots[size+1].getNum();
		long l1 = (num2&0x00000000ffffffffL)<<32;
		long l2 = num1&0x00000000ffffffffL;
		
		
		return l1|l2;
	}
	
	public void pushDouble(double val){
		long i = Double.doubleToLongBits(val);
		pushLong(i);
	}
	
	public double popDouble(){	
		long i = popLong();
		return Double.longBitsToDouble(i);
	}
	
	public void pushRef(Object val){
		slots[size].setRef(val);
		size++;
	}
	
	public Object popRef(){
		size--;
		return slots[size].getRef();
	}
	
	public void pushSlot(Slot slot) {
	    Slot copy = new Slot();
	    copy.setNum(slot.getNum());
	    copy.setRef(slot.getRef());
	    slots[size] = copy;
	    size++;
	}
	
	public Slot popSlot() {
	    size--;
	    return slots[size];
	}
	
	public int getSize() {
	    return size;
	}


    public Object getRefFromTop(int n) {
        // TODO Auto-generated method stub
        return slots[size -1 - n].getRef();
    }
    
    
    public void pushBoolean(boolean val) {
        if (val) {
            this.pushInt(1);
        } else {
            this.pushInt(0);
        }
    }
    
    public boolean popBoolean() {
        return this.popInt() == 1;
    }

}
