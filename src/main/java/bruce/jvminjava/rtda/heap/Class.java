package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.U2;
import bruce.jvminjava.classanalyzer.attribute.CodeAttribute;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantUtf8Info;
import bruce.jvminjava.instructions.ClassFileHelper;

import static bruce.jvminjava.rtda.heap.AccessFlags.*;

public class Class {
    private int accessFlags;
    private String name;
    private String superClassName;
    private List<String> interfaceNames;
    private List<Object> constantPool;
    private List<Field> fields;
    private List<Method> methods;
    private Object loader;
    private Class superClass;
    private List<Class> interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Object staticVars;

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public List<String> getInterfaceNames() {
        return interfaceNames;
    }

    public void setInterfaceNames(List<String> interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public List<Object> getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(List<Object> constantPool) {
        this.constantPool = constantPool;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public Object getLoader() {
        return loader;
    }

    public void setLoader(Object loader) {
        this.loader = loader;
    }

    public Class getSuperClass() {
        return superClass;
    }

    public void setSuperClass(Class superClass) {
        this.superClass = superClass;
    }

    public List<Class> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Class> interfaces) {
        this.interfaces = interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public Object getStaticVars() {
        return staticVars;
    }

    public void setStaticVars(Object staticVars) {
        this.staticVars = staticVars;
    }

    public Class(ClassFile cf) {
        this.accessFlags = (int) cf.getAccessFlags().getValue();
        this.name = readStringFromConstantPool(cf.getConstantPool(), cf.getClassName());
        this.superClassName = readStringFromConstantPool(cf.getConstantPool(), cf.getSuperClass());
        List<String> interfaceNames = new ArrayList<String>();
        for (U2 i : cf.getInterfaces()) {
            interfaceNames.add(readStringFromConstantPool(cf.getConstantPool(), i));
        }
        this.interfaceNames = interfaceNames;
        this.fields = convertFields(cf);
        this.methods = convertMethods(cf);
    }

    private static String readStringFromConstantPool(List<ConstantInfo> constantPool, U2 index) {
        ConstantUtf8Info val = (ConstantUtf8Info) constantPool.get((int) index.getValue());
        return val.toString();
    }

    private List<Field> convertFields(ClassFile cf) {
        List<Field> fields = new ArrayList<>();
        for (bruce.jvminjava.classanalyzer.ClassMember cm : cf.getFields()) {
            Field f = new Field();
            f.setName(readStringFromConstantPool(cf.getConstantPool(), cm.getNameIndex()));
            f.setAccessFlags((int)cm.getAccessFlag().getValue());
            f.setDescriptor(readStringFromConstantPool(cf.getConstantPool(), cm.getDescriptorIndex()));
            f.setKlass(this);
            fields.add(f);
        }
        return fields;
    }
    
    
    private List<Method> convertMethods(ClassFile cf) {
        List<Method> methods = new ArrayList<>();
        for (bruce.jvminjava.classanalyzer.ClassMember cm : cf.getMethods()) {
            Method m = new Method();
            m.setName(readStringFromConstantPool(cf.getConstantPool(), cm.getNameIndex()));
            m.setAccessFlags((int)cm.getAccessFlag().getValue());
            m.setDescriptor(readStringFromConstantPool(cf.getConstantPool(), cm.getDescriptorIndex()));
            m.setKlass(this);
            
            CodeAttribute codeAttr = ClassFileHelper.getCodeAttribute(cm);
            m.setMaxLocals((int)codeAttr.getMaxLocals().getValue());
            m.setMaxStack((int)codeAttr.getMaxStack().getValue());
            m.setCode(ClassFileHelper.converCodeListToBytes(codeAttr.getCodes()));
        }
        return methods;
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags & ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags & ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & ACC_ENUM);
    }
}
