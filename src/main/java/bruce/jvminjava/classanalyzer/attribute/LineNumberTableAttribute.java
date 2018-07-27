package bruce.jvminjava.classanalyzer.attribute;

import java.util.List;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class LineNumberTableAttribute extends Attribute{
	@Element(index = 3)
	private U2 length;

	@Element(index = 4)
	private List<LineNumber> lineNumberTable;
	
	public U2 getLength() {
		return length;
	}

	public void setLength(U2 length) {
		this.length = length;
	}

	public List<LineNumber> getLineNumberTable() {
		return lineNumberTable;
	}

	public void setLineNumberTable(List<LineNumber> lineNumberTable) {
		this.lineNumberTable = lineNumberTable;
	}

	public static class LineNumber extends ClassElement{
        @Element(index = 1)
		private U2 start;

        @Element(index = 2)
		private U2 lineNumber;
		
		public U2 getStart() {
			return start;
		}
		public void setStart(U2 start) {
			this.start = start;
		}
		public U2 getLineNumber() {
			return lineNumber;
		}
		public void setLineNumber(U2 lineNumber) {
			this.lineNumber = lineNumber;
		}
		
	}
	
}
