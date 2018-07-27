package bruce.jvminjava;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.rmi.server.UID;
import java.util.Scanner;

import bruce.jvminjava.classanalyzer.ClassReader;
import bruce.jvminjava.classanalyzer.U1;
import bruce.jvminjava.classpath.Entry;
import bruce.jvminjava.classpath.ZipEntry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Bruce JVM started!" );
        Jvm jvm = new Jvm();
        jvm.start();
//        Scanner scanner = new Scanner(System.in);
//        String cmdText = scanner.nextLine();
//        cmdText.startsWith("java");
//        System.out.println(cmdText);
        
        
//        ZipEntry zipEntry = new ZipEntry("D:\\abc.jar");
//        byte[] a = zipEntry.readClass("javassist.convert.TransformAccessArrayField");
//        System.out.println("--------------"+a.length);
//        System.out.println(new String(a, Charset.forName("utf8")));
        
//        File directory = new File(".");//设定为当前文件夹 
//        System.out.println(directory.getAbsolutePath());
//        if (exists("./target"))
//		{
//        	try {
//				System.out.println("11111111111111111111");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//获取标准的路径 
//		}
        
//        Entry zipEntry = Entry.newEntry("D:\\java\\workspace\\jvminjava\\target\\classes");
//        byte[] a = zipEntry.readClass("bruce.jvminjava.ClassFileTest");
//        ClassReader reader = new ClassReader(a);
////        reader.readAndCheckMagic();
////        reader.readAndCheckVersion();
////        reader.readConstantPoolCount();
// //       reader.read();
//       
//        System.out.println(new U1(intToBytes(18)).getValue());
//        System.out.println(new U1(intToBytes(18)).toHexString());
//        
//        ClassFileTest test = new ClassFileTest();
        
    }
    
    public static byte[] intToBytes2(int value) 
	{ 
		byte[] src = new byte[4];
		src[0] = (byte) ((value>>24) & 0xFF);
		src[1] = (byte) ((value>>16)& 0xFF);
		src[2] = (byte) ((value>>8)&0xFF);  
		src[3] = (byte) (value & 0xFF);		
		return src;
	}
    
    public static byte[] intToByteArray(int a) {
	    return new byte[] {
	        (byte) ((a >> 24) & 0xFF),
	        (byte) ((a >> 16) & 0xFF),   
	        (byte) ((a >> 8) & 0xFF),   
	        (byte) (a & 0xFF)
	    };
	}
    
    public static byte[] intToBytes( int value ) 
	{ 
		byte[] src = new byte[4];
		src[3] =  (byte) ((value>>24) & 0xFF);
		src[2] =  (byte) ((value>>16) & 0xFF);
		src[1] =  (byte) ((value>>8) & 0xFF);  
		src[0] =  (byte) (value & 0xFF);				
		return src; 
	}
    
    private static boolean exists(String path){
		boolean result = false;
		File file = new File(path);
		if (file.exists()) {
			result = true;
		}
		return result;
	}
   
    
}
