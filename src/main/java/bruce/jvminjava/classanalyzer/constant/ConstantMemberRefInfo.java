package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public abstract class ConstantMemberRefInfo extends ConstantInfo{
	@Element(index = 2)
	protected U2 classIndex;
	@Element(index = 3)
	protected U2 nameAndTypeIndex;
	
	public U2 getClassIndex() {
		return classIndex;
	}
	public void setClassIndex(U2 classIndex) {
		this.classIndex = classIndex;
	}
	public U2 getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
	public void setNameAndTypeIndex(U2 nameAndValueIndex) {
		this.nameAndTypeIndex = nameAndValueIndex;
	}
}
