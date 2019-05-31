package bruce.jvminjava.rtda;

import bruce.jvminjava.rtda.heap.Object;


public class LocalVars {
	public Slot[] vars;
	public LocalVars(int size){
		if (size > 0){
			vars = new Slot[size];
			for (int i = 0; i< size; i++){
				vars[i] = new Slot();
			}
		}
	}
	
	public void setInt(int index, int val){
		vars[index].setNum(val);
	}
	
	public int getInt(int index){	 
		return vars[index].getNum();
	}
	
	public void setFloat(int index, float val){
		vars[index].setNum(Float.floatToRawIntBits(val));
	}
	
	public float getFloat(int index){	 
		return Float.intBitsToFloat(vars[index].getNum());
	}
	
	public void setLong(int index, long val){
		vars[index].setNum((int)val);
		vars[index+1].setNum((int)(val>>32));
	}
	
	public long getLong(int index){	 
		int num1 = vars[index].getNum();
		int num2 = vars[index+1].getNum();
		long l1 = (num2&0x00000000ffffffffL)<<32;
		long l2 = num1&0x00000000ffffffffL;
		return l1|l2;
	}
	
	public void setDouble(int index, double val){
		long i = Double.doubleToLongBits(val);
		setLong(index, i);
	}
	
	public double getDouble(int index){	
		long i = getLong(index);
		return Double.longBitsToDouble(i);
	}
	
	public void setRef(int index, Object val){
		vars[index].setRef(val);
	}
	
	public Object getRef(int index){	 
		return vars[index].getRef();
	}

    public void setSlot(int index, Slot slot) {
        // TODO Auto-generated method stub
        vars[index] = slot;
    }
    
    public Object getThis() {
        return getRef(0);
    }
}
