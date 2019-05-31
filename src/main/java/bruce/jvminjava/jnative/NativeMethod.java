package bruce.jvminjava.jnative;

import bruce.jvminjava.rtda.Frame;

public abstract class NativeMethod {
    
    private String registerClassName;
    private String registerMethodName;
    private String registerMethodDescriptor;
    
    public NativeMethod(String registerClassName, String registerMethodName, String registerMethodDescriptor) {
        super();
        this.registerClassName = registerClassName;
        this.registerMethodName = registerMethodName;
        this.registerMethodDescriptor = registerMethodDescriptor;
    }
    public String getRegisterClassName() {
        return registerClassName;
    };
    protected String getRegisterMethodName() {
        return registerMethodName;
    }
    protected String getRegisterMethodDescriptor() {
        return registerMethodDescriptor;
    }
    public abstract void invoke(Frame frame);
}
