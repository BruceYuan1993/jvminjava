package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.ClassReader;
import bruce.jvminjava.classpath.Classpath;

public class ClassLoader {
    Classpath classpath;
    Map<String, Class> classMap;
    
    public ClassLoader(Classpath classpath) {
        //classPath = new Classpath(jreOption, cpOption)
        this.classpath = classpath;
        classMap = new HashMap<>();
    }
    
    public Class loadClass(String name) {
        if (this.classMap.containsKey(name)) {
            return this.classMap.get(name);
        }
        if (name.charAt(0) == '[') {
            return loadArrayClass(name);
        }
        return loadNonArrayClass(name);
    }

    private Class loadArrayClass(String name) {
        // TODO Auto-generated method stub
        Class klass = new Class();
        klass.setAccessFlags(AccessFlags.ACC_PUBLIC);
        klass.setName(name);
        klass.setLoader(this);
        klass.startInit();
        klass.setSuperClass(loadClass("java/lang/Object"));
        List<Class> interfaces = new ArrayList<>();
        interfaces.add(loadClass("java/lang/Cloneable"));
        interfaces.add(loadClass("java/io/Serializable"));
        klass.setInterfaces(interfaces);
        classMap.put(name, klass);
        return klass;
    }

    private Class loadNonArrayClass(String name) {
        // TODO Auto-generated method stub
        try {
            Class klass = defineClass(readClass(name));
            link(klass);
            System.out.println("Load " +  name);
            return klass; 
        } catch (Exception e) {
            System.out.println("Load failed: " + name);
            throw e;
        }
        
    }
    
    private void link(Class klass) {
        // TODO Auto-generated method stub
        verify(klass);
        prepare(klass);
    }

    private void prepare(Class klass) {
        // TODO Auto-generated method stub
        calcInstanceFieldSlotIds(klass);
        calcStaticFieldSlotIds(klass);
        allocAndInitStaticVars(klass);
    }

    private void calcInstanceFieldSlotIds(Class klass) {
        // TODO Auto-generated method stub
        int slotId = 0;
        if (klass.getSuperClass() != null) {
            slotId = klass.getSuperClass().getInstanceSlotCount();
        }
        for (Field f : klass.getFields()) {
            if (!f.isStatic()) {
                f.setSlotId(slotId);
                slotId++;
                if (f.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        klass.setInstanceSlotCount(slotId);
    }
    
    private void calcStaticFieldSlotIds(Class klass) {
        // TODO Auto-generated method stub
        int slotId = 0;
        for (Field f : klass.getFields()) {
            if (f.isStatic()) {
                f.setSlotId(slotId);
                slotId++;
                if (f.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        klass.setStaticSlotCount(slotId);
    }
    
    private void allocAndInitStaticVars(Class klass) {
        klass.setStaticVars(new Slots(klass.getStaticSlotCount()));
        for (Field f : klass.getFields()) {
            if (f.isStatic() && f.isFinal()) {
                initStaticFinalVar(klass, f);
            }
        }
        
    }

    private void initStaticFinalVar(Class klass, Field f) {
        // TODO Auto-generated method stub
        Slots slots = klass.getStaticVars();
        ConstantPool cp = klass.getConstantPool();
        int cpIndex = f.getConstValueIndex();
        int slotId = f.getSlotId();
        
        if (cpIndex > 0) {
            switch (f.getDescriptor()) {
            case "Z":
            case "B":
            case "C":
            case "S":
            case "I":
                {
                    int val = (int)cp.getConstant(cpIndex);
                    slots.setInt(slotId, val);
                }
                break;
            case "J":
            {
                long val = (long)cp.getConstant(cpIndex);
                slots.setLong(slotId, val);
            }
                break;
            case "F":
            {
                float val = (float)cp.getConstant(cpIndex);
                slots.setFloat(slotId, val);
            }
                break;
            case "D":
            {
                double val = (double)cp.getConstant(cpIndex);
                slots.setDouble(slotId, val);
            }
                break;
            case "Ljava/lang/String;":
            {
                String val = (String)cp.getConstant(cpIndex);
                Object jStr = StringPool.INSTANCE.jString(klass.getLoader(), val);
                slots.setRef(slotId, jStr);
            }
                break;
            default:
                break;
            }
        }
    }

    private void verify(Class klass) {
        // TODO Auto-generated method stub
        // Bypass
    }

    private byte[] readClass(String name) {
        byte[] data = classpath.readClass(name);
        return data;
    }

    private Class defineClass(byte[] data) {
        // TODO Auto-generated method stub
        Class klass = parseClass(data);
        klass.setLoader(this);
        resolveSuperClass(klass);
        resolveInterfaces(klass);
        classMap.put(klass.getName(), klass);
        return klass;
    }
    
    private Class parseClass(byte[] data) {
        // TODO Auto-generated method stub
        ClassReader classReader = new ClassReader(data);
        ClassFile classFile;
        try {
            classFile = classReader.read();
            return new Class(classFile);
        } catch (Exception e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        return null;
    }
    
    private void resolveSuperClass(Class klass) {
        if (!klass.getName().equals("java/lang/Object")) {
            klass.setSuperClass(klass.getLoader().loadClass(klass.getSuperClassName()));
        }
    }
    
    private void resolveInterfaces(Class klass) {
        int size =  klass.getInterfaceNames() == null ? 0 : klass.getInterfaceNames().size();
        if (size > 0) {
            List<Class> interfaces = new ArrayList<>();
            
            for (String interfaceName : klass.getInterfaceNames()) {
                interfaces.add(klass.getLoader().loadClass(interfaceName));
            }
            
            klass.setInterfaces(interfaces);
        }
    }
}
