package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class ConstantInvokeDynamicInfo extends ConstantInfo{
	@Element(index = 2)
	U2 bootstrapMethodAttrIndex;
	@Element(index = 3)
	U2 nameAndTypeIndex;
	
	public ConstantInvokeDynamicInfo(U2 bootstrapMethodAttrIndex, U2 nameAndValueIndex) {
		this();
		this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
		this.nameAndTypeIndex = nameAndValueIndex;
	}

	public ConstantInvokeDynamicInfo() {

	}

	public U2 getBootstrapMethodAttrIndex() {
		return bootstrapMethodAttrIndex;
	}

	public void setBootstrapMethodAttrIndex(U2 bootstrapMethodAttrIndex) {
		this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
	}

	public U2 getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	public void setNameAndTypeIndex(U2 nameAndTypeIndex) {
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	
}
