package bruce.jvminjava.classanalyzer.attribute;

import java.util.List;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

public class LocalVariableTableAttribute extends Attribute {
    @Element(index = 3)
	private U2 length;

    @Element(index = 4)
	private List<LocalVariable> localVariableTable;
	
	public U2 getLength() {
		return length;
	}


	public void setLength(U2 length) {
		this.length = length;
	}


	public List<LocalVariable> getLocalVariableTable() {
		return localVariableTable;
	}


	public void setLocalVariableTable(List<LocalVariable> localVariableTable) {
		this.localVariableTable = localVariableTable;
	}


	public static class LocalVariable extends ClassElement{
	    @Element(index = 1)
		private U2 start;

        @Element(index = 2)
		private U2 length;

        @Element(index = 3)
		private U2 nameIndex;

        @Element(index = 4)
		private U2 desriptorIndex;

        @Element(index = 5)
		private U2 index;

		public U2 getStart() {
			return start;
		}
		public void setStart(U2 start) {
			this.start = start;
		}
		public U2 getLength() {
			return length;
		}
		public void setLength(U2 length) {
			this.length = length;
		}
		public U2 getNameIndex() {
			return nameIndex;
		}
		public void setNameIndex(U2 nameIndex) {
			this.nameIndex = nameIndex;
		}
		public U2 getDesriptorIndex() {
			return desriptorIndex;
		}
		public void setDesriptorIndex(U2 desriptorIndex) {
			this.desriptorIndex = desriptorIndex;
		}
		public U2 getIndex() {
			return index;
		}
		public void setIndex(U2 index) {
			this.index = index;
		}
	}
}
