package bruce.jvminjava.classpath;

import java.io.File;
import java.util.List;

public abstract class MultipleEntry implements Entry{

	protected List<Entry> entries;
	
	MultipleEntry(String path)
	{
		initEntries(path);
	}
	
	@Override
	public byte[] readClass(String className) {
		// TODO Auto-generated method stub
		byte[] result = null;
		for (Entry entry : entries) {
			result = entry.readClass(className);
			if (result != null)
			{
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		for (Entry entry : entries) {
			builder.append(entry.toString());
			builder.append(File.pathSeparator);
		}
		if (builder.length() > 0)
		{
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}
	
	protected abstract void initEntries(String path);

}