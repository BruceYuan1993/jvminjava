package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U8;

public class ConstantLongInfo extends ConstantInfo{
	@Element(index = 2)
	private U8 data;

	public ConstantLongInfo(U8 data) {
		this();
		this.data = data;
	}
	public ConstantLongInfo() {

	}
	
	public long getValue(){
		return data.getValue();
	}
}
