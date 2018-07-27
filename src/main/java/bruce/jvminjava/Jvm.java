package bruce.jvminjava;

import bruce.jvminjava.rtda.LocalVars;

public class Jvm {
	public void start(){
//	    Frame frame = new Frame(100, 100);
//	    testLocalVars(frame.getLocalVars());
	}
	
	private void testLocalVars(LocalVars vars) {
	    vars.setInt(0, 100);
	    vars.setInt(1, -100);
	    vars.setLong(2, 299792458);
	    vars.setLong(4, 299792458);
	    
	    
	    System.out.println(vars.getInt(1));
	    System.out.println(vars.getLong(2));
	    
	}
}
