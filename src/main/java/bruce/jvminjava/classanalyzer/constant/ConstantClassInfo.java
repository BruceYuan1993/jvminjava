package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class ConstantClassInfo extends ConstantInfo{
	@Element(index = 2)
	private U2 nameIndex;
	
	public ConstantClassInfo(U2 nameIndex) {
		this();
		this.nameIndex = nameIndex;
	}

	public ConstantClassInfo() { }

	public U2 getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(U2 nameIndex) {
		this.nameIndex = nameIndex;
	}
}
