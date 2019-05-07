package bruce.jvminjava.rtda.heap;

public class FieldReference extends MemberReference{
    private Field field;

    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
}
