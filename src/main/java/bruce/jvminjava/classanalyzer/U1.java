package bruce.jvminjava.classanalyzer;


public class U1 extends UBase{
    public U1(byte[] data) {
        super(data, 1);
    }

    @Override
    public long read(byte[] data) {
        return Byte.toUnsignedInt(data[0]);
    }
}
