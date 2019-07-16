package bruce.jvminjava.jnative.java.lang.system;

import bruce.jvminjava.jnative.NativeMethod;
import bruce.jvminjava.rtda.Frame;
import bruce.jvminjava.rtda.LocalVars;
import bruce.jvminjava.rtda.heap.Object;
import bruce.jvminjava.rtda.heap.Array;
import bruce.jvminjava.rtda.heap.Class;

public class ArrayCopy extends NativeMethod {

    public ArrayCopy() {
        super("java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V");
    }

    @Override
    public void invoke(Frame frame) {
        // TODO Auto-generated method stub
        LocalVars localVars = frame.getLocalVars();
        Array src = (Array)localVars.getRef(0);
        int srcPos = localVars.getInt(1);
        Array dest = (Array) localVars.getRef(2);
        int destPos = localVars.getInt(3);
        int length = localVars.getInt(4);
        
        if (src == null || dest == null) {
            throw new RuntimeException("NullPointerException");
        }
        
        if (!checkArrayCopy(src, dest)) {
            throw new RuntimeException("ArrayStoreException");
        }
        
        if (srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos+length > src.getArrayLength() ||
                destPos+length > dest.getArrayLength()) {
            throw new RuntimeException("java.lang.IndexOutOfBoundsException");
        }
        
        
    }
    
    private boolean checkArrayCopy(Object src, Object dest) {
        Class srcClass = src.getKlass();
        Class destClass = dest.getKlass();
        if (!srcClass.isArray() || !destClass.isArray()) {
            return false; 
        }
        
        if (srcClass.getCompomentClass().isPrimitive() || 
                destClass.getCompomentClass().isPrimitive()) {
            return srcClass == destClass;
        }
        return true;
    }
    
    
    void arrayCopy(Array src, Array dest , int srcPos, int destPos, int length) {
        java.lang.Object srcData = src.getData();
        
        if (srcData.getClass() == byte[].class) {
            byte[] _src = src.getBytes();
            byte[] _dest = dest.getBytes();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == short[].class) {
            short[] _src = src.getShorts();
            short[] _dest = dest.getShorts();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == int[].class) {
            int[] _src = src.getInts();
            int[] _dest = dest.getInts();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == long[].class) {
            long[] _src = src.getLongs();
            long[] _dest = dest.getLongs();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == char[].class) {
            char[] _src = src.getChars();
            char[] _dest = dest.getChars();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == float[].class) {
            float[] _src = src.getFloats();
            float[] _dest = dest.getFloats();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == double[].class) {
            double[] _src = src.getDoubles();
            double[] _dest = dest.getDoubles();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        } else if (srcData.getClass() == Object[].class) {
            Object[] _src = src.getRefs();
            Object[] _dest = dest.getRefs();
            System.arraycopy(_src, srcPos, _dest, destPos, length);
        }
    }
    
}
