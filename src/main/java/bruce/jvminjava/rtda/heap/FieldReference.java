package bruce.jvminjava.rtda.heap;

import java.util.List;
import java.util.Optional;

public class FieldReference extends MemberReference{
    private Field field;

    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
    
    
    public Field resolvedField() {
        if (this.getField() == null) {
            resolveFieldRef();
        }
        return this.getField();
    }

    private void resolveFieldRef() {
        Class d = this.getConstantPool().getKlass();
        Class c = resolvedClass();
        
        Field field = lookupField(c, getName(), getDescriptor());
        
        if (field == null) {
            throw new RuntimeException("java.lang.NoSuchFieldError");
        }
        if (!field.isAccessibleTo(d)) {
            throw new RuntimeException("Illegal access error");
        }
        this.field = field;
    }
    
    
    Field lookupField(Class c, String name, String descriptor) {
        List<Field> fields = c.getFields();
        if (fields != null && fields.size() > 0) {
            Optional<Field> field = fields.stream().filter(x -> x.getName().equals(name) && x.getDescriptor().equals(descriptor))
                .findFirst();
            if (field.isPresent()) {
                return field.get();
            }
        }
        
        List<Class> interfaces = c.getInterfaces();
        if (interfaces != null && interfaces.size()>0) {
            for (Class x : interfaces) {
                Field f = lookupField(x, name, descriptor);
                if (f != null) {
                    return f;
                }
            }
        }
        
        
        if (c.getSuperClass() != null) {
            return lookupField(c.getSuperClass(), name, descriptor);
        }
        
        return null;
    }
}
