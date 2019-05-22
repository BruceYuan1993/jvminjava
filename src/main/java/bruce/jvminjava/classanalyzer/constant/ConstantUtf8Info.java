package bruce.jvminjava.classanalyzer.constant;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import bruce.jvminjava.classanalyzer.Element;
import bruce.jvminjava.classanalyzer.U2;


public class ConstantUtf8Info extends ConstantInfo{
	@Element(index = 2)
	private U2 length;
	@Element(index = 3)
	private byte[] data;
	private String value;

	@Override
	public String toString() {
	    if (data == null || data.length == 0) {
	        return null;
	    }
		try {
			return new String(decodeMUtf8(data), "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ConstantUtf8Info(U2 length, byte[] data) {
		this();
		this.length = length;
		this.data = data;
		try {
			value = new String(decodeMUtf8(data), "utf8");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public ConstantUtf8Info() {

	}
	
	public String getValue(){
		return value;
	}


	private byte[] decodeMUtf8(byte[] src) {
		// TODO Auto-generated method stub
		if (src != null && src.length > 0){
			byte[] bytes = new byte[src.length];
			int skip = 0;
			for (int i = 0; i < src.length; i++) {
				if (src[i] == (byte)0xC0 &&
						i + 1 < src.length && src[i+1] == (byte)0x80){
					bytes[i] = ((byte)0);
					i++;
					skip++;
				}
				else {
					bytes[i] = src[i];
				}
			}
			return Arrays.copyOf(bytes, bytes.length - skip);
		}
		return null;
	}
}
