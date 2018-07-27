package bruce.jvminjava.classpath;

import java.io.File;

public interface Entry {
	byte[] readClass(String className);
	
	static Entry newEntry(String path) {
		if (path == null){
			throw new InvalidClassPathParameterException("path cannot be null");
		}
		if (path.contains(File.pathSeparator)) {
			return new CompositeEntry(path);
		} else if (path.endsWith("*")) {
			return new WildcardEntry(path);
		} else if (path.endsWith(".jar") || path.endsWith(".JAR") ||
				path.endsWith(".zip") || path.endsWith(".ZIP")) {
			return new ZipEntry(path);
		} else {
			return new DirEntry(path);
		}
	}
	
	public static String concatFilePath(String basePath, String... paths) {
		StringBuilder builder = new StringBuilder(basePath);
		if (paths != null)
		{
			for (String path : paths){
				if (path != null){
					if (!path.startsWith(File.separator) && builder.charAt(builder.length() - 1) != File.separatorChar){
						builder.append(File.separatorChar);
					}
					builder.append(path);
				}
			}
		}
		return builder.toString();
	}
}
