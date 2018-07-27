package bruce.jvminjava.classanalyzer;

public abstract class ClassElement {
    // 表示该元素在class文件byte流中的起始位置
    private long offset;
    // 表示该元素所占byte的长度
    private int elementLength;

    // getter and setter
    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getElementLength() {
        return elementLength;
    }

    public void setElementLength(int elementLength) {
        this.elementLength = elementLength;
    }
}