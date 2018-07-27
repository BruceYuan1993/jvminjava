package bruce.jvminjava.classanalyzer.attribute;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class ConstantValueAttribute extends Attribute{

	@Element(index = 3)
	private U2 constantValueIndex;

	public U2 getConstantValueIndex() {
		return constantValueIndex;
	}

	public void setConstantValueIndex(U2 constantValueIndex) {
		this.constantValueIndex = constantValueIndex;
	}


}
