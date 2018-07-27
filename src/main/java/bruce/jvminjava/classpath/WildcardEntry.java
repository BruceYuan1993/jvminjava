package bruce.jvminjava.classpath;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class WildcardEntry extends MultipleEntry{
	WildcardEntry(String path) {
		super(path);
	}

	@Override
	protected void initEntries(String path) {
		// TODO Auto-generated method stub
		path = path.substring(0, path.length() - 1);
		File file = new File(path);
		String[] jarFiles = file.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if (name.endsWith(".jar") || name.endsWith(".JAR")){
					return true;
				}
				return false;
			}
		});
		entries = new ArrayList<Entry>(jarFiles.length);
		for (String item : jarFiles) {
			entries.add(new ZipEntry(path + item));
		}
	}
}
