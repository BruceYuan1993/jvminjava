package bruce.jvminjava.classanalyzer.attribute;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class SourceFileAttribute extends Attribute{

    @Element(index = 3)
	private U2 sourceIndex;

	public U2 getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(U2 sourceIndex) {
		this.sourceIndex = sourceIndex;
	}
}
