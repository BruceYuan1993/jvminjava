package bruce.jvminjava.classpath;

import java.io.File;

public class Classpath {
	private Entry bootPath;
	private Entry extPath;
	private Entry userPath;
	
	private static final String JRE_CURRENTPATH = "./jre";
	private static final String JAVA_HOME = "JAVA_HOME";
	private static final String LIB = "lib";
	private static final String EXT = "ext";
	private static final String WILDCARD = "*";
	
	public Classpath(String jreOption, String cpOption) {
		parseBootPathAndExtPath(jreOption);
		parseUserPath(cpOption);
	}
	
	@Override
	public String toString() {
		return this.userPath.toString();
	}

	private void parseBootPathAndExtPath(String jreOption) {
		String jrePath = getJreDir(jreOption);
		
		String libPath = Entry.concatFilePath(jrePath, LIB, WILDCARD);
		this.bootPath = new WildcardEntry(libPath);
		
		String extPath = Entry.concatFilePath(jrePath, LIB, EXT, WILDCARD);
		this.extPath = new WildcardEntry(extPath);
	}
	
	private String getJreDir(String jreOption) {
		String result = null;
		if (jreOption!= null && !"".equals(jreOption) && exists(jreOption)){
			result = jreOption;
		} else if(exists(JRE_CURRENTPATH))
		{
			result = JRE_CURRENTPATH;
		} else {
			result = System.getenv(JAVA_HOME);
		}
		if (result == null) {
			throw new RuntimeException("Cannot find jre folder.");
		}	
		return result;	
	}
	
	private boolean exists(String path){
		boolean result = false;
		File file = new File(path);
		if (file.exists()) {
			result = true;
		}
		return result;
	}
	
	private void parseUserPath(String cpOption){
		this.userPath = Entry.newEntry(cpOption);
	}
	
	public byte[] readClass(String className){
		byte[] result = bootPath.readClass(className);
		if (result == null) {
			result = extPath.readClass(className);
		}
		if (result == null) {
			result = userPath.readClass(className);
		}
		return result;
	}
	
}
