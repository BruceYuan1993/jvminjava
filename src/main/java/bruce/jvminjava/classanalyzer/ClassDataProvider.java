package bruce.jvminjava.classanalyzer;

public abstract class ClassDataProvider {
    private long position;

    protected abstract byte[] readBytesInternal(int len);

    public byte[] readBytes(int len){
        return readBytes(len, false);
    }

    public byte[] readBytes(int len, boolean peek){
        byte[] result = readBytesInternal(len);
        if (!peek) {
            position += len;
        }
        return result;
    }

    U1 readU1() {
        return readU1(false);
    }

    U1 readU1(boolean peek) {
        return new U1(readBytes(1, peek));
    }

    U2 readU2() {
        return readU2(false);
    }

    U2 readU2(boolean peek) {
        return new U2(readBytes(2, peek));
    }

    U4 readU4() {
        return readU4(false);
    }

    U4 readU4(boolean peek) {
        return new U4(readBytes(4, peek));
    }

    U8 readU8() {
        return readU8(false);
    }

    U8 readU8(boolean peek) {
        return new U8(readBytes(8, peek));
    }

    public long getPosition() {
        return position;
    }
}
