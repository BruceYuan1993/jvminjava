package bruce.jvminjava;

import bruce.jvminjava.classpath.Classpath;
import bruce.jvminjava.instructions.Interpreter;
import bruce.jvminjava.rtda.heap.ClassLoader;
import bruce.jvminjava.rtda.heap.Method;

public class Jvm {
	public void start(){
	    String className = "bruce.jvminjava.GetClassTest";
	    Classpath classPath = new Classpath("C:\\Program Files\\Java\\jre1.8.0_211","D:\\jvm\\jvminjava\\target\\classes");
	    ClassLoader classLaoder = new ClassLoader(classPath);
	    
	    className = className.replace(".", "/");
	    bruce.jvminjava.rtda.heap.Class mainClass = classLaoder.loadClass(className);
	    Method mainMethod = mainClass.getMainMethod();
	    if (mainMethod != null) {
            Interpreter interpreter = new Interpreter();
            String[] args = new String[] {"代理模式","文章目录"};
            interpreter.interpret(mainMethod, args);
  	    } else {
	        System.out.println("Main method not found in class " + className);
	    }
	}
}
