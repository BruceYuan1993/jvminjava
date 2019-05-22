package bruce.jvminjava.rtda.heap;

public class MethodReference extends MemberReference{
    private Method method;

    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
}
