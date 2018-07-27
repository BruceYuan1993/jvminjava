package bruce.jvminjava.classanalyzer;

import java.nio.ByteBuffer;

public class U4 extends UBase{
    public U4(byte[] data) {
        super(data, 4);
    }

    @Override
    public long read(byte[] data) {
        return Integer.toUnsignedLong(ByteBuffer.wrap(data).getInt());
    }
}
