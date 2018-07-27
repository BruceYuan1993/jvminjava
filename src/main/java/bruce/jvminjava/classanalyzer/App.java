package bruce.jvminjava.classanalyzer;

import java.util.List;

import bruce.jvminjava.classanalyzer.attribute.Attribute;
import bruce.jvminjava.classpath.Entry;

public class App {
    public static void main( String[] args ) throws Exception {
        Entry zipEntry = Entry.newEntry("D:\\jvm\\jvminjava\\target\\classes");
        byte[] result = zipEntry.readClass("bruce.jvminjava.ClassFileTest");
        
        ClassReader reader = new ClassReader(result);
        ClassFile a = reader.read();
        List<Attribute> code = a.getMethods().get(0).getAttributes();
        System.out.println(code);
    }


}