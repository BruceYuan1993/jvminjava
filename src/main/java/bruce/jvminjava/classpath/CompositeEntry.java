package bruce.jvminjava.classpath;

import java.io.File;
import java.util.ArrayList;

public class CompositeEntry extends MultipleEntry {
	CompositeEntry(String path) {
		super(path);
	}

	@Override
	protected void initEntries(String path) {
		// TODO Auto-generated method stub
		String[] strs = path.split(File.pathSeparator);
		entries = new ArrayList<Entry>(strs.length);
		for (String item : strs) {
			//trim space
			entries.add(Entry.newEntry(item));
		}
	}
}
