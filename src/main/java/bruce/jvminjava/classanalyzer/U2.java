package bruce.jvminjava.classanalyzer;

import java.nio.ByteBuffer;

public class U2 extends UBase{
    public U2(byte[] data) {
        super(data, 2);
    }

    @Override
    public long read(byte[] data) {
        return ByteBuffer.wrap(data).getChar();
    }
}
