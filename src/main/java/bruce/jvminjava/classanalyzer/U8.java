package bruce.jvminjava.classanalyzer;

import java.nio.ByteBuffer;

/**
 * Created by bruceyuan on 17-8-15.
 */
public class U8 extends UBase{
    public U8(byte[] data) {
        super(data, 8);
    }

    @Override
    public long read(byte[] data) {
        return ByteBuffer.wrap(data).getLong();
    }
}