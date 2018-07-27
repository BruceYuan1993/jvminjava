package bruce.jvminjava.classanalyzer.constant;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.Table;
import bruce.jvminjava.classanalyzer.U2;

/**
 * Created by bruceyuan on 17-9-17.
 */

/*
    cp_info {
        u1 tag;
        u1 info[];
    }
 */
public class ConstantPool extends ClassElement{
    @Element(index = 1)
    private U2 count;

    @Element(index = 2)
    private Table<ConstantInfo> items;
}
