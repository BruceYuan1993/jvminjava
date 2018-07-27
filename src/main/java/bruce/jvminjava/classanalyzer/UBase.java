package bruce.jvminjava.classanalyzer;


/**
 * 无符号数的基类
 *
 * Class文件是一组以字节为基础单位的二进制流，当需要存放字节以上的元素时，会按照高位在前（大端存储）
 * 的方式拆分成多个字节进行存储。Class文件中有两种数据结构：无符号数和表。
 * 其中无符号数属于基本的数据类型，可以表示数字、索引引用和字符串。
 * 表是由多个无符号数或者其他表作为元素组成的复合数据类型。
 * 因此我们看的出来无符号数是整个Class文件的基本的数据类型，由无符号数和
 * 以无符号数为基础的表类型组成了整个Class文件。
 *
 * 无符号数可以分为：
 *  U1 -- 1个字节无符号数 -- N/A
 *     -- 1个字节有符号数 -- byte
 *  U2 -- 2个字节无符号数 -- char
 *     -- 2个字节有符号数 -- short
 *  U4 -- 4个字节无符号数 -- N/A
 *     -- 4个字节有符号数 -- int
 *  U8 -- 8个字节无符号数 -- N/A
 *     -- 8个字节有符号数 -- long
 *
 * 从上面我们可以看出来在Java中缺少足够的原生数据类型来表示无符号数，
 * 再加上为了代码的可读性（我们在描述Class文件的时候往往用U1/U2/U4/U8来称呼元素的类型），
 * 我们来自定义这些基本数据类型。
 *
 */

public abstract class UBase extends ClassElement {
    private static final String HexPrefix = "0x";

    // 无符号整数的byte数据源
    private byte[] data;

    /**
     * 同样字节表示的无符号数的上限一定是比其有符号数的上限要大的。
     * 在Java中，对于U1类型的整数表示我们要用short以上的类型来承载，
     * 对于U2要用int以上，依次类推。因此对于U8类型，用long来表示是有溢出风险的。
     *
     *  在设计无符号数的数据结构时，我当时考虑了两种实现方式：
     *  1. 将UBase设计成一个泛型类型，泛型参数表示value的类型
     *      public abstract class UBase<T> {
     *          T value
     *      }
     *
     *      public class U1 extends UInt<Integer>{
     *
     *      }
     *
     *   2.直接用long来表示value
     */
    private long value;

    public UBase(byte[] data, int expectedDataLen) {
        if (data == null || data.length != expectedDataLen){
            throw new AnalyzException("Invaild parameters");
        }
        this.data = data;
        this.value = read(data);
    }

    public abstract long read(byte[] data);

    public byte[] getData() {
        return data;
    }

    public long getValue() {
        return value;
    }

    public String toHexString() {
        return HexPrefix + Long.toHexString(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}