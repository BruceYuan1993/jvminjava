package bruce.jvminjava.rtda.heap;

import java.util.ArrayList;
import java.util.List;

public class MethodDescriptor {
    private List<String> parameterTypes = new ArrayList<>();
    private String returnType;
    
    
    public List<String> getParameterTypes() {
        return parameterTypes;
    }


    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }


    public String getReturnType() {
        return returnType;
    }


    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }


    public  void addParameterType(String t) {
        parameterTypes.add(t);
    }
}
