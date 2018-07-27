package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U4;

public class ConstantFloatInfo extends ConstantInfo{
	@Element(index = 2)
	private U4 data;

	public ConstantFloatInfo(U4 data) {
		this();
		this.data = data;
	}
	public ConstantFloatInfo() {

	}
	
	public double getValue(){
		return Double.longBitsToDouble(data.getValue());
	}
}
