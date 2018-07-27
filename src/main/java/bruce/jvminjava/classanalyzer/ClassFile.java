package bruce.jvminjava.classanalyzer;

import java.util.List;

import bruce.jvminjava.classanalyzer.attribute.Attribute;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;

/**
 * Created by bruceyuan on 17-8-15.
 */
public class ClassFile extends ClassElement{
    public ClassVersion getVersion() {
        return version;
    }

    public void setVersion(ClassVersion version) {
        this.version = version;
    }

    public U2 getCpCount() {
        return cpCount;
    }

    public void setCpCount(U2 cpCount) {
        this.cpCount = cpCount;
    }

    public U2 getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(U2 accessFlags) {
        this.accessFlags = accessFlags;
    }

    public U2 getClassName() {
        return className;
    }

    public void setClassName(U2 thisClass) {
        this.className = thisClass;
    }

    public U2 getSuperClass() {
        return superClass;
    }

    public void setSuperClass(U2 superClass) {
        this.superClass = superClass;
    }

    public U2 getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(U2 interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public List<U2> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<U2> interfaces) {
        this.interfaces = interfaces;
    }

    public U2 getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(U2 fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public List<ClassMember> getFields() {
        return fields;
    }

    public void setFields(List<ClassMember> fields) {
        this.fields = fields;
    }

    public U2 getMethodsCount() {
        return methodsCount;
    }

    public void setMethodsCount(U2 methodsCount) {
        this.methodsCount = methodsCount;
    }

    public List<ClassMember> getMethods() {
        return methods;
    }

    public void setMethods(List<ClassMember> methods) {
        this.methods = methods;
    }

    public U2 getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(U2 attributesCount) {
        this.attributesCount = attributesCount;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    /*
        ClassFile {
            u4             magic;
            u2             minor_version;
            u2             major_version;
            u2             constant_pool_count;
            cp_info        constant_pool[constant_pool_count-1];
            u2             access_flags;
            u2             this_class;
            u2             super_class;
            u2             interfaces_count;
            u2             interfaces[interfaces_count];
            u2             fields_count;
            field_info     fields[fields_count];
            u2             methods_count;
            method_info    methods[methods_count];
            u2             attributes_count;
            attribute_info attributes[attributes_count];
        }
    */
    @Element(index = 1)
    private ClassVersion version;

    @Element(index = 2)
    private U2 cpCount;

    @Element(index = 3)
    private List<ConstantInfo> constantPool;

    public List<ConstantInfo> getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(List<ConstantInfo> constantPool) {
        this.constantPool = constantPool;
    }

    @Element(index = 4)
    private U2 accessFlags;

    @Element(index = 5)
    private U2 className;

    @Element(index = 6)
    private U2 superClass;

    @Element(index = 7)
    private U2 interfacesCount;

    @Element(index = 8)
    private List<U2> interfaces;

    @Element(index = 9)
    private U2 fieldsCount;

    @Element(index = 10)
    private List<ClassMember> fields;

    @Element(index = 11)
    private U2 methodsCount;

    @Element(index = 12)
    private List<ClassMember> methods;

    @Element(index = 13)
    private U2 attributesCount;

    @Element(index = 14)
    private List<Attribute> attributes;

}