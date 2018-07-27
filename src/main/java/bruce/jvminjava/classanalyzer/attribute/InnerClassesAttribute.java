package bruce.jvminjava.classanalyzer.attribute;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

import java.util.List;

public class InnerClassesAttribute extends Attribute{
	@Element(index = 3)
	private U2 length;

    @Element(index = 4)
	private List<InnerClass>  innerClasses;
	public static class InnerClass extends ClassElement{
        @Element(index = 1)
		private U2 innerClassInfoIndex;
        @Element(index = 2)
		private U2 outerClassInfoIndex;
        @Element(index = 3)
		private U2 innerNameIndex;
        @Element(index = 4)
		private U2 innerClassAccessFlags;
	}
}
