package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.ConstantFieldref;

import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.constant.ConstantClassInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantDoubleInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantFieldRefInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantFloatInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantIntegerInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantLongInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantUtf8Info;

public class ConstantPool {
    private Class klass;
    private List<Constant> consts;
    
    public ConstantPool(Class klass, ClassFile cf) {
        this.klass = klass;
        
    }
    
    private List<Constant> covertConstantPool(List<ConstantInfo> cp) {
        consts = new ArrayList<Constant>(cp.size());
        for (int i=1; i < cp.size(); i++) {
            Object o = null;
            ConstantInfo constInfo = cp.get(i);
            switch ((int)(constInfo.getTag().getValue())) {
                case ConstantInfo.CONSTANT_INTEGER_INFO:
                o = ((ConstantIntegerInfo)constInfo).getValue();
                break;
            case ConstantInfo.CONSTANT_FLOAT_INFO:
                o = ((ConstantFloatInfo)constInfo).getValue();
                break;
            case ConstantInfo.CONSTANT_LONG_INFO:
                o = ((ConstantLongInfo)constInfo).getValue();
                i++;
                break;
            case ConstantInfo.CONSTANT_DOUBLE_INFO:
                o = ((ConstantDoubleInfo)constInfo).getValue();
                i++;
                break;
            case ConstantInfo.CONSTANT_STRING_INFO:
                o = ((ConstantUtf8Info)constInfo).getValue().toString();
            case ConstantInfo.CONSTANT_CLASS_INFO:
                // ((ConstantClassInfo)constInfo).getNameIndex().getValue()
                ClassReference classRef = new ClassReference();
                classRef.setClassName(klass.getName());
                classRef.setConstantPool(this);
                classRef.setKlass(klass);
            case ConstantInfo.CONSTANT_FIELDREF_INFO:
                FieldReference fieldRef = new FieldReference();
                fieldRef.setClassName(klass.getName());
                fieldRef.setConstantPool(this);
                fieldRef.setKlass(klass);
                
                ((ConstantFieldRefInfo)constInfo).get
                fieldRef.setName(name);
                fieldRef.setDescriptor(descriptor);
            }
        }
        return consts;
    }
    
    public Constant getConstant(int index) {
        if (consts == null || consts.size() <= index) {
            return null;
        }
        return consts.get(index);
    }
}
