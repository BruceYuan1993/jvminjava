package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.U2;
import bruce.jvminjava.classanalyzer.attribute.Attribute;
import bruce.jvminjava.classanalyzer.attribute.CodeAttribute;
import bruce.jvminjava.classanalyzer.attribute.ConstantValueAttribute;
import bruce.jvminjava.classanalyzer.constant.ConstantClassInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantUtf8Info;
import bruce.jvminjava.instructions.ClassFileHelper;

import static bruce.jvminjava.rtda.heap.AccessFlags.*;

public class Class {
    private int accessFlags;
    private String name;
    private String superClassName;
    private List<String> interfaceNames;
    private ConstantPool constantPool;
    private List<Field> fields;
    private List<Method> methods;
    private ClassLoader loader;
    private Class superClass;
    private List<Class> interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Slots staticVars;

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

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
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

    public ClassLoader getLoader() {
        return loader;
    }

    public void setLoader(ClassLoader loader) {
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

    public Slots getStaticVars() {
        return staticVars;
    }

    public void setStaticVars(Slots staticVars) {
        this.staticVars = staticVars;
    }
    public Class() {
        
    }
    
    public Class(ClassFile cf) {
        this.accessFlags = (int) cf.getAccessFlags().getValue();
        ConstantClassInfo className = (ConstantClassInfo) cf.getConstantPool().get((int)cf.getClassName().getValue());
        this.name = readStringFromConstantPool(cf.getConstantPool(), className.getNameIndex());
        
        if (cf.getSuperClass() != null) {
            ConstantClassInfo superClassName = (ConstantClassInfo) cf.getConstantPool().get((int)cf.getSuperClass().getValue());
            if (superClassName != null) {
                this.superClassName = readStringFromConstantPool(cf.getConstantPool(), superClassName.getNameIndex());
            }
            
        }
        
        List<String> interfaceNames = new ArrayList<String>();
        for (U2 i : cf.getInterfaces()) {
            ConstantClassInfo interfaceName = (ConstantClassInfo) cf.getConstantPool().get((int)i.getValue());
            interfaceNames.add(readStringFromConstantPool(cf.getConstantPool(), interfaceName.getNameIndex()));
        }
        this.interfaceNames = interfaceNames;
        this.fields = convertFields(cf);
        this.methods = convertMethods(cf);
        this.constantPool = new ConstantPool(this, cf); 
    }

    public static String readStringFromConstantPool(List<ConstantInfo> constantPool, U2 index) {
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
            if (cm.getAttributesCount().getValue() > 0) {
                Optional<Attribute> attr = cm.getAttributes().stream().filter(x -> x.getClass() == ConstantValueAttribute.class).findFirst();
                if (attr.isPresent()) {
                    int index = (int) ((ConstantValueAttribute)attr.get()).getConstantValueIndex().getValue(); 
                    f.setConstValueIndex(index);
                }
            }
           
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
            if (codeAttr != null) {
                m.setMaxLocals((int)codeAttr.getMaxLocals().getValue());
                m.setMaxStack((int)codeAttr.getMaxStack().getValue());
                m.setCode(ClassFileHelper.converCodeListToBytes(codeAttr.getCodes()));
            }
            m.setArgSlotCount(calcArgSlotCount(m));
            methods.add(m);
        }
        return methods;
    }
    
    public int calcArgSlotCount(Method method) {
        int argSlotCount = 0;
        MethodDescriptorParser parser = new MethodDescriptorParser();
        MethodDescriptor parsedDescriptor = parser.parse(method.getDescriptor());
        for (String paramType : parsedDescriptor.getParameterTypes()) {
            argSlotCount++;
            if (paramType.equals("J") || paramType.equals("D")) {
                argSlotCount++;
            }
        }
        
        if (!method.isStatic()) {
            argSlotCount++;
        }
        return argSlotCount;
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
    
    public String getPackageName() {
        return name.substring(0, name.lastIndexOf("/"));
    }
    boolean isAccessibleTo(Class c) {
        return isPublic() || getPackageName().equals(c.getPackageName());
    }
    
    public boolean isSubClassOf(Class c) {
        for (Class k = this.getSuperClass(); k != null; k = k.getSuperClass()) {
            if (k == c) {
                return true;
            }
        }
        return false;
    }

    public boolean isAssignableFrom(Class other) {
        // TODO Auto-generated method stub
        if (other == this) {
            return true;
        }
        
        if (!other.isArray()) {
            if (!other.isInterface()) {
                if (!this.isInterface()) {
                    return other.isSubClassOf(this);
                } else {
                    return other.isImplements(this);
                }
            } else {
                if (!this.isInterface()) {
                    return this.isJlObject();
                } else {
                    return this.isSubInterfaceOf(other);
                }
            }
        } else {
            if (!this.isArray()) {
                if (!this.isArray()) {
                    return this.isJlObject();
                } else {
                    return this.isJlCloneable() || this.isJioSerializable();
                }
            } else {
                Class sc = other.getCompomentClass();
                Class tc = this.getCompomentClass();
                return sc == tc || tc.isAssignableFrom(sc);
            }
        }
        
        
    }
    
    public boolean isImplements(Class iface) {
        for (Class c = this; c != null; c = c.getSuperClass()) {
            for (Class i :c.getInterfaces()) {
                if (i == iface || i.isSubInterfaceOf(iface)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSubInterfaceOf(Class iface) {
        // TODO Auto-generated method stub
        for (Class superInterface : this.getInterfaces()) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    public Method getMainMethod() {
        // TODO Auto-generated method stub
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }
    

    public Method getStaticMethod(String name, String descriptor) {
        for (Method method : this.getMethods()) {
            if (method.isStatic() &&
                method.getName().equals(name) &&
                method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public boolean isSuperClassOf(Class other) {
        // TODO Auto-generated method stub
        return other.isSubClassOf(this);
    }
    
    private boolean initStarted = false;
    public boolean initStarted() {
        return initStarted;
    }
    
    public void startInit() {
        initStarted = true;
    }

    public Method getClinitMethod() {
        // TODO Auto-generated method stub
        return getStaticMethod("<clinit>", "()V");
    }
    
    
    public boolean isArray() {
        return this.name.charAt(0) == '[';
    }
    
    public Object newObject() {
        Object obj = new Object(this, new Slots(getInstanceSlotCount()));
        return obj;
    } 
    
    public Array newArray(int count) {
        if (!this.isArray()) {
            throw new RuntimeException("Not array class: " + name);
        }
        
        switch (name) {
            case "[Z":
                return new Array(this, new byte[count]);
            case "[B":
                return new Array(this, new byte[count]);
            case "[C":
                return new Array(this, new char[count]);
            case "[S":
                return new Array(this, new char[count]);
            case "[I":
                return new Array(this, new int[count]);
            case "[J":
                return new Array(this, new long[count]);
            case "[F":
                return new Array(this, new float[count]);
            case "[D":
                return new Array(this, new double[count]);
            default:
                return new Array(this, new Object[count]);
        }
    }
    
    public Class getArrayClass() {
        String arrayClassName = getArrayClassName();
        return loader.loadClass(arrayClassName);
    }

    private String getArrayClassName() {
        return "[" + toDescriptor(this.name);
    }

    
    private static Map<String, String> primitiveTypes = new HashMap<>();
    static {
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }
    
    private String toDescriptor(String className) {
        // TODO Auto-generated method stub
        if (className.charAt(0) == '[') {
            return className;
        }
        if (primitiveTypes.containsKey(className)) {
            primitiveTypes.get(className);
        }
        return "L" + className + ";";
    }

   public Class getCompomentClass() {
        String compontentClassName = getCompontentClassName(name);
        return loader.loadClass(compontentClassName);
    }

    private String getCompontentClassName(String name) {
        // TODO Auto-generated method stub
        if (name.charAt(0) == '[') {
            String compontentTypeDescriptor = name.substring(1);
            return toClassName(compontentTypeDescriptor);
        }
        throw new RuntimeException("Not array: " + name);
    }

    private String toClassName(String descriptor) {
        // TODO Auto-generated method stub
        if (descriptor.charAt(0) == '[') {
            return descriptor;
        }
        if (descriptor.charAt(0) == 'L') {
            return descriptor.substring(1, descriptor.length() - 1);
        }
        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(descriptor)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Invalid descriptor: " + name);
    }
    
    private boolean isJlObject() {
        return "java/lang/Object".equals(this.name);
    }
    private boolean isJlCloneable() {
        return "java/lang/Cloneable".equals(this.name);
    }
    private boolean isJioSerializable() {
        return "java/io/Serializable".equals(this.name);
    }

    public Field getField(String name, String descriptor, boolean isStatic) {
        for (Class c = this; c != null; c = c.getSuperClass()) {
            for (Field f : c.getFields()) {
                if (f.isStatic() == isStatic &&
                        f.getName().equals(name) &&
                        f.getDescriptor().equals(descriptor)) {
                    return f;
                }
            }
        }
        return null;
    }
}
