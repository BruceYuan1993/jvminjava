package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U1;
import bruce.jvminjava.classanalyzer.U2;

public class ConstantMethodHandleInfo extends ConstantInfo{
	@Element(index = 2)
	private U1 referenceKind;
	@Element(index = 3)
	private U2 referenceIndex;

	public ConstantMethodHandleInfo(U1 referenceKind, U2 referenceIndex) {
		this();
		this.referenceKind = referenceKind;
		this.referenceIndex = referenceIndex;
	}
	
	public ConstantMethodHandleInfo() {

	}

	public U1 getReferenceKind() {
		return referenceKind;
	}

	public void setReferenceKind(U1 referenceKind) {
		this.referenceKind = referenceKind;
	}

	public U2 getReferenceIndex() {
		return referenceIndex;
	}

	public void setReferenceIndex(U2 referenceIndex) {
		this.referenceIndex = referenceIndex;
	}
}
