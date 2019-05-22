package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class ConstantStringInfo extends ConstantInfo{
	@Element(index = 2)
	//string_index
	private U2 nameIndex;
	
	public ConstantStringInfo(U2 nameIndex) {
		this();
		this.nameIndex = nameIndex;
	}

	public ConstantStringInfo() {

	}

	public U2 getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(U2 nameIndex) {
		this.nameIndex = nameIndex;
	}
}
