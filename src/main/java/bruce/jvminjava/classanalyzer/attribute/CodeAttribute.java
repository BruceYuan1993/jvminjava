package bruce.jvminjava.classanalyzer.attribute;

import java.util.List;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U1;
import bruce.jvminjava.classanalyzer.U2;
import bruce.jvminjava.classanalyzer.U4;

public class CodeAttribute extends Attribute{
    @Element(index = 3)
	private U2 maxStack;
    @Element(index = 4)
	private U2 maxLocals;
    @Element(index = 5)
	private U4 codeLength;
    @Element(index = 6)
	private List<U1> codes;
    @Element(index = 7)
	private U2 exceptionLength;
    @Element(index = 8)
	private List<Exception> exceptions;
    @Element(index = 9)
    private U2 attributesCount;
    @Element(index = 10)
    private List<Attribute> attributes;

	public U2 getAttributesCount() {
		return attributesCount;
	}

	public void setAttributesCount(U2 attributesCount) {
		this.attributesCount = attributesCount;
	}



	public List<Attribute> getAttributes() {
		return attributes;
	}



	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	
	
	public U2 getMaxStack() {
		return maxStack;
	}



	public void setMaxStack(U2 maxStack) {
		this.maxStack = maxStack;
	}



	public U2 getMaxLocals() {
		return maxLocals;
	}



	public void setMaxLocals(U2 maxLocals) {
		this.maxLocals = maxLocals;
	}



	public U4 getCodeLength() {
		return codeLength;
	}



	public void setCodeLength(U4 codeLength) {
		this.codeLength = codeLength;
	}



	public List<U1> getCodes() {
		return codes;
	}



	public void setCodes(List<U1> codes) {
		this.codes = codes;
	}



	public U2 getExceptionLength() {
		return exceptionLength;
	}



	public void setExceptionLength(U2 exceptionLength) {
		this.exceptionLength = exceptionLength;
	}



	public List<Exception> getExceptions() {
		return exceptions;
	}



	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}



	public static class Exception extends ClassElement{
        @Element(index = 1)
		private U2 start;
        @Element(index = 2)
		private U2 end;
        @Element(index = 3)
		private U2 handler;
        @Element(index = 4)
		private U2 catchType;

		public U2 getStart() {
			return start;
		}
		public void setStart(U2 start) {
			this.start = start;
		}
		public U2 getEnd() {
			return end;
		}
		public void setEnd(U2 end) {
			this.end = end;
		}
		public U2 getHandler() {
			return handler;
		}
		public void setHandler(U2 handler) {
			this.handler = handler;
		}
		public U2 getCatchType() {
			return catchType;
		}
		public void setCatchType(U2 catchType) {
			this.catchType = catchType;
		}
	}
}
