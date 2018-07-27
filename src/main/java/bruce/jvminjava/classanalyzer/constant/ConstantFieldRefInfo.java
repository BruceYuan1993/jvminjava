package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.U2;

public class ConstantFieldRefInfo extends ConstantMemberRefInfo{
	public ConstantFieldRefInfo(U2 classIndex, U2 nameAndValueIndex) {
		this();
		this.classIndex = classIndex;
		this.nameAndTypeIndex = nameAndValueIndex;
	}

	public ConstantFieldRefInfo() {

	}
}
