package bruce.jvminjava.classanalyzer.attribute;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;

/**
 * Created by bruceyuan on 17-9-21.
 */
public class SignatureAttribute extends Attribute {
    @Element(index = 3)
    private U2 signatureIndex;
}
