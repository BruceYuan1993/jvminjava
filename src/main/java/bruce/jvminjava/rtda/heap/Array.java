package bruce.jvminjava.rtda.heap;

public class Array extends Object{
    public Array(Class klass, java.lang.Object data) {
        super(klass, data);
    }

    public byte[] getBytes() {
        return (byte[])data;
    }
    
    public short[] getShorts() {
        return (short[])data;
    }
    
    public int[] getInts() {
        return (int[])data;
    }
    
    public long[] getLongs() {
        return (long[])data;
    }
    
    public char[] getChars() {
        return (char[])data;
    }
    
    public float[] getFloats() {
        return (float[])data;
    }
    
    public double[] getDoubles() {
        return (double[])data;
    }
    
    public Object[] getRefs() {
        return (Object[])data;
    }
    
    public int getArrayLength() {
        if (data instanceof byte[]) {
            return ((byte[])data).length;
        } else if (data instanceof short[]) {
            return ((short[])data).length;
        } else if (data instanceof int[]) {
            return ((int[])data).length;
        } else if (data instanceof long[]) {
            return ((long[])data).length;
        } else if (data instanceof char[]) {
            return ((char[])data).length;
        } else if (data instanceof float[]) {
            return ((float[])data).length;
        } else if (data instanceof double[]) {
            return ((double[])data).length;
        } else if (data instanceof Object[]) {
            return ((Object[])data).length;
        }
        throw new RuntimeException("Cann't get array length");
    }
}
