package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U1;

public abstract class ConstantInfo extends ClassElement{
    public static final int CONSTANT_UTF8_INFO = 1;
    public static final int CONSTANT_INTEGER_INFO = 3;
    public static final int CONSTANT_FLOAT_INFO = 4;
    public static final int CONSTANT_LONG_INFO = 5;
    public static final int CONSTANT_DOUBLE_INFO = 6;
    public static final int CONSTANT_CLASS_INFO = 7;
    public static final int CONSTANT_STRING_INFO = 8;
    public static final int CONSTANT_FIELDREF_INFO = 9;
    public static final int CONSTANT_METHODREF_INFO = 10;
    public static final int CONSTANT_INTERFACEMETHODREF_INFO = 11;
    public static final int CONSTANT_NAMEANDTYPE_INFO = 12;
    public static final int CONSTANT_METHODHANDLE_INFO = 15;
    public static final int CONSTANT_METHODTYPE_INFO = 16;
    public static final int CONSTANT_INVOKEDYNAMIC_INFO = 18;


    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_SUPER = 0x0021;
    public static final int ACC_INTERFACE = 0x0200;
    public static final int ACC_ABSTRACT = 0x0400;
    public static final int ACC_SYNTHETIC = 0x1000;
    public static final int ACC_ANNOTATION = 0x2000;
    public static final int ACC_ENUM = 0x4000;

    @Element(index = 1)
    protected U1 tag;

    public U1 getTag() {
        return tag;
    }

    public void setTag(U1 tag) {
        this.tag = tag;
    }


}
