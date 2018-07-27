package bruce.jvminjava.classanalyzer.attribute;

import bruce.jvminjava.classanalyzer.ClassElement;
import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

import java.util.List;

/**
 * Created by bruceyuan on 17-9-21.
 */
public class BootstrapMethodsAttribute extends Attribute{
    @Element(index = 3)
    private U2 length;
    @Element(index = 4)
    private List<BootstrapMethod> bootstrapMethods;

    public static class BootstrapMethod extends ClassElement{
        //private U2 bootstrapMethodRef;
        //private
    }
}
