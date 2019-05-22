package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.List;


import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.U2;
import bruce.jvminjava.classanalyzer.constant.ConstantClassInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantDoubleInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantFieldRefInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantFloatInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantIntegerInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantInterfaceMethodRefInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantLongInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantMethodRefInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantNameAndTypeInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantStringInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantUtf8Info;

public class ConstantPool {
    private Class klass;
    private List<java.lang.Object> consts;

    public ConstantPool(Class klass, ClassFile cf) {
        this.klass = klass;
        this.consts = covertConstantPool(cf.getConstantPool());
    }

    public Class getKlass() {
        return klass;
    }

    private List<java.lang.Object> covertConstantPool(List<ConstantInfo> cp) {
        consts = new ArrayList<java.lang.Object>(cp.size());
        int j = 0;
        while (j < cp.size()) {
            consts.add(null);
            j++;
        }
        
        for (int i = 1; i < cp.size(); i++) {
            java.lang.Object o = null;
            ConstantInfo constInfo = cp.get(i);
            switch ((int) (constInfo.getTag().getValue())) {
            case ConstantInfo.CONSTANT_INTEGER_INFO:
                o = (int)((ConstantIntegerInfo) constInfo).getValue();
                consts.set(i, o);
                break;
            case ConstantInfo.CONSTANT_FLOAT_INFO:
                o = (float)((ConstantFloatInfo) constInfo).getValue();
                consts.set(i, o);
                break;
            case ConstantInfo.CONSTANT_LONG_INFO:
                o = ((ConstantLongInfo) constInfo).getValue();
                i++;
                consts.set(i, o);
                break;
            case ConstantInfo.CONSTANT_DOUBLE_INFO:
                o = ((ConstantDoubleInfo) constInfo).getValue();
                i++;
                consts.set(i, o);
                break;
            case ConstantInfo.CONSTANT_STRING_INFO:
                ConstantStringInfo stringInfo = (ConstantStringInfo) constInfo;
                o = Class.readStringFromConstantPool(cp, stringInfo.getNameIndex());
                consts.set(i, o);
                break;
            case ConstantInfo.CONSTANT_UTF8_INFO:
                ConstantUtf8Info utf8 = (ConstantUtf8Info) constInfo;
                o = utf8.getValue();
                consts.set(i, o);
                break;
            case ConstantInfo.CONSTANT_CLASS_INFO:
                String name1 = Class.readStringFromConstantPool(cp, ((ConstantClassInfo) constInfo).getNameIndex());
                ClassReference classRef = new ClassReference();
                classRef.setClassName(name1);
                classRef.setConstantPool(this);
                //classRef.setKlass(klass);
                consts.set(i, classRef);
                break;
            case ConstantInfo.CONSTANT_FIELDREF_INFO:{
                U2 classIndex = ((ConstantFieldRefInfo) constInfo).getClassIndex();
                ConstantClassInfo classCons = (ConstantClassInfo)(cp.get((int)classIndex.getValue()));
                String className = Class.readStringFromConstantPool(cp, classCons.getNameIndex());
                FieldReference fieldRef = new FieldReference();
                fieldRef.setClassName(className);
                fieldRef.setConstantPool(this);
                //fieldRef.setKlass(klass);
                U2 u2 = ((ConstantFieldRefInfo) constInfo).getNameAndTypeIndex();
                ConstantNameAndTypeInfo nameAndType = (ConstantNameAndTypeInfo) cp.get((int) u2.getValue());
                String name = Class.readStringFromConstantPool(cp, nameAndType.getNameIndex());
                fieldRef.setName(name);
                String descriptor = Class.readStringFromConstantPool(cp, nameAndType.getDescriptorIndex());
                fieldRef.setDescriptor(descriptor);
                consts.set(i, fieldRef);
                }
                break;
            case ConstantInfo.CONSTANT_METHODREF_INFO: {
                U2 classIndex = ((ConstantMethodRefInfo) constInfo).getClassIndex();
                ConstantClassInfo classCons = (ConstantClassInfo)(cp.get((int)classIndex.getValue()));
                String className = Class.readStringFromConstantPool(cp, classCons.getNameIndex());
                MethodReference methodRef = new MethodReference();
                methodRef.setClassName(className);
                methodRef.setConstantPool(this);
                // methodRef.setKlass(klass);
                U2 index = ((ConstantMethodRefInfo) constInfo).getNameAndTypeIndex();
                ConstantNameAndTypeInfo methodNameAndType = (ConstantNameAndTypeInfo) cp.get((int) index.getValue());
                String methodName = Class.readStringFromConstantPool(cp, methodNameAndType.getNameIndex());
                methodRef.setName(methodName);
                String methodDescriptor = Class.readStringFromConstantPool(cp, methodNameAndType.getDescriptorIndex());
                methodRef.setDescriptor(methodDescriptor);
                consts.set(i, methodRef);
            }
                break;
            case ConstantInfo.CONSTANT_INTERFACEMETHODREF_INFO:{
                U2 classIndex = ((ConstantInterfaceMethodRefInfo) constInfo).getClassIndex();
                ConstantClassInfo classCons = (ConstantClassInfo)(cp.get((int)classIndex.getValue()));
                String className = Class.readStringFromConstantPool(cp, classCons.getNameIndex());
                
                InterfaceMethodReference methodRef = new InterfaceMethodReference();
                methodRef.setClassName(className);
                methodRef.setConstantPool(this);
                //methodRef.setKlass(klass);
                U2 index = ((ConstantInterfaceMethodRefInfo) constInfo).getNameAndTypeIndex();
                ConstantNameAndTypeInfo methodNameAndType = (ConstantNameAndTypeInfo) cp.get((int) index.getValue());
                String methodName = Class.readStringFromConstantPool(cp, methodNameAndType.getNameIndex());
                methodRef.setName(methodName);
                String methodDescriptor = Class.readStringFromConstantPool(cp, methodNameAndType.getDescriptorIndex());
                methodRef.setDescriptor(methodDescriptor);
                consts.set(i, methodRef);
            }
                break;
            }
        }
        return consts;
    }

    public java.lang.Object getConstant(int index) {
        if (consts == null || consts.size() <= index) {
            return null;
        }
        return consts.get(index);
    }
}
