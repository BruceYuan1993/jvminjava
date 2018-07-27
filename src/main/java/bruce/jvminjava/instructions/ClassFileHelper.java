package bruce.jvminjava.instructions;

import java.util.List;

import bruce.jvminjava.classanalyzer.ClassMember;
import bruce.jvminjava.classanalyzer.U1;
import bruce.jvminjava.classanalyzer.attribute.Attribute;
import bruce.jvminjava.classanalyzer.attribute.CodeAttribute;

public abstract class ClassFileHelper {
    public static CodeAttribute getCodeAttribute(ClassMember member) {
        for (Attribute attr : member.getAttributes()) {
            if (attr instanceof CodeAttribute) {
                return (CodeAttribute) attr;
            }
        }
        return null;
        
    }
    
    
    public static byte[] converCodeListToBytes(List<U1> codes) {
        byte[] bytes = new byte[codes.size()];
        for (int i = 0; i < codes.size(); i++) {
            bytes[i] = (byte)codes.get(i).getValue();
        }
        return bytes;
    } 

}
