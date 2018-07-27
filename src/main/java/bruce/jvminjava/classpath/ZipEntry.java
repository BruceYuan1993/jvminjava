package bruce.jvminjava.classpath;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.zip.ZipFile;
public class ZipEntry extends SingleEntry {
	//private static final int BUFF_SIZE = 1024;
	
	@Override
	public byte[] readClass(String className) {
		// TODO Auto-generated method stub
		byte[] result = null;
		try (ZipFile zipFile = new ZipFile(absPath);){
			
			StringBuilder entryPathBuilder = new StringBuilder(className.replace('.', '/'));
			entryPathBuilder.append(".class");
			String entryPath = entryPathBuilder.toString();
			
			java.util.zip.ZipEntry entry = zipFile.getEntry(entryPath);
			if (entry == null) {
				throw new InvalidClassPathParameterException(entryPath);
			}else {
				if (entry.getSize() > Integer.MAX_VALUE)
				{
					throw new RuntimeException("The class file is too big");
				}
				int size = (int) entry.getSize();
				result = new byte[size];
				BufferedInputStream br = 
						new BufferedInputStream(zipFile.getInputStream(entry));
//				byte[] buff = new byte[BUFF_SIZE];
//				
//				int pos = 0;
//				while (pos < size) {
//					int len = br.read(buff, pos, buff.length);					
//					System.arraycopy(buff, 0, result, pos, len);
//					pos += len;
//				}
				br.read(result);
                br.close();  
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	ZipEntry(String absPath) {
		File dir = new File(absPath);
		if (!dir.exists() || !dir.isFile()){
			throw new InvalidClassPathParameterException(absPath);
		}
		this.absPath = absPath;
	}
}
