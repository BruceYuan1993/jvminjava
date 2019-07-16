package bruce.jvminjava.rtda.heap;

import java.util.HashMap;
import java.util.Map;

public enum StringPool {
    INSTANCE;
    private Map<String, Object> internedStrings = new HashMap<>();
    
    public Object jString(ClassLoader loader, String originStr) {
        if (internedStrings.containsKey(originStr)) {
            return internedStrings.get(originStr);
        }
        
        char[] chars = originStr.toCharArray();
        Array jchars = new Array(loader.loadClass("[C"), chars);
        Object str = loader.loadClass("java/lang/String").newObject();
        str.setRefVar("value","[C", jchars);
        
        internedStrings.put(originStr, str);
        return str;
    }
    
    public String originString(Object jStr) {
        Object charArr = jStr.getRefVar("value", "[C");
        return new String(((Array)charArr).getChars());
    }

    public Object internString(Object jStr) {
        String originStr = originString(jStr);
        
        if (internedStrings.containsKey(originStr)) {
            return internedStrings.get(originStr);
        }
        
        internedStrings.put(originStr, jStr);
        return jStr;
    }
}
