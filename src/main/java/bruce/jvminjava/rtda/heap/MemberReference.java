package bruce.jvminjava.rtda.heap;

public class MemberReference extends SymbolicReference{
    private String name;
    private String descriptor;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescriptor() {
        return descriptor;
    }
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}