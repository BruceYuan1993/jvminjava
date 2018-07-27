package bruce.jvminjava.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DirEntry extends SingleEntry {
	//private static final int BUFF_SIZE = 1024;
	@Override
	public byte[] readClass(String className) {
		// TODO Auto-generated method stub
//		String fullPath = Entry.concatFilePath(absPath, className.replace('.', File.separatorChar));
		StringBuilder fullPathBuilder = new StringBuilder(absPath);
		fullPathBuilder.append(File.separatorChar);
		fullPathBuilder.append(className.replace('.', File.separatorChar));
		fullPathBuilder.append(".class");
		String fullPath = fullPathBuilder.toString();
		File classFile = new File(fullPath);
		if (!classFile.exists()){
			throw new InvalidClassPathParameterException(fullPath);
		}
		byte[] result = null;
		try (FileChannel fc = new FileInputStream(classFile).getChannel()){
			if (fc.size() > Integer.MAX_VALUE)
			{
				throw new RuntimeException("The class file is too big");
			}
//			ByteBuffer buff = ByteBuffer.allocate(BUFF_SIZE);
//			int pos = 0;
//			int size = (int) fc.size();
//			result = new byte[size];
//			while (pos < size) {
//				fc.read(buff, pos);
//				buff.flip();
//				byte[] part = buff.array();
//				System.arraycopy(part, 0, result, pos, buff.limit());
//				pos += buff.limit();
//			}
			
			ByteBuffer buff = ByteBuffer.allocate((int) fc.size());
			fc.read(buff);
			buff.flip();
			result = buff.array();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	DirEntry(String absDir) {
		File dir = new File(absDir);
		if (!dir.exists() || !dir.isDirectory()){
			throw new InvalidClassPathParameterException(absDir);
		}
		this.absPath = absDir;
	}
}
