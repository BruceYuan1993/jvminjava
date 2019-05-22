package bruce.jvminjava.instructions;

import java.util.List;

import bruce.jvminjava.classanalyzer.ClassFile;
import bruce.jvminjava.classanalyzer.ClassMember;
import bruce.jvminjava.classanalyzer.ClassReader;
import bruce.jvminjava.classanalyzer.U2;
import bruce.jvminjava.classanalyzer.constant.ConstantInfo;
import bruce.jvminjava.classanalyzer.constant.ConstantUtf8Info;
import bruce.jvminjava.classpath.Entry;

public class Test {
    public static void main( String[] args ) throws Exception {
        Entry zipEntry = Entry.newEntry("D:\\jvm\\jvminjava\\target\\classes");
        byte[] result = zipEntry.readClass("bruce.jvminjava.GaussTest");
        
        ClassReader reader = new ClassReader(result);
        ClassFile cf = reader.read();
        List<ConstantInfo> constantPool =  cf.getConstantPool();
        
        for (ClassMember method : cf.getMethods()) {
            if (readStringFromConstantPool(constantPool, method.getNameIndex()).equals("main")){
                //Interpreter interpreter = new Interpreter();
                //interpreter.interpret(method);
            }
        }
    }
    
    private static String readStringFromConstantPool(List<ConstantInfo> constantPool, U2 index){
        ConstantUtf8Info val = (ConstantUtf8Info)constantPool.get((int)index.getValue());
        return val.toString();
    }


}