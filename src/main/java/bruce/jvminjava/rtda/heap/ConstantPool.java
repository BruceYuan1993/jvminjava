package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.constant.ConstantDoubleInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantFloatInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantIntegerInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantLongInfo;

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
