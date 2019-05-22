package bruce.jvminjava.rtda.heap;

public class InterfaceMethodReference extends MemberReference{
    private Method method;

    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
}
