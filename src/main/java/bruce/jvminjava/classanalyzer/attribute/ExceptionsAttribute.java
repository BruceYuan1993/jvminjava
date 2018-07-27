package bruce.jvminjava.classanalyzer.attribute;

import java.util.List;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class ExceptionsAttribute extends Attribute{
    @Element(index = 3)
	private U2 length;
    @Element(index = 4)
	private List<U2> exceptionIndexs;

	public U2 getLength() {
		return length;
	}
	public void setLength(U2 length) {
		this.length = length;
	}
	public List<U2> getExceptionIndexs() {
		return exceptionIndexs;
	}
	public void setExceptionIndexs(List<U2> exceptionIndexs) {
		this.exceptionIndexs = exceptionIndexs;
	}
}
