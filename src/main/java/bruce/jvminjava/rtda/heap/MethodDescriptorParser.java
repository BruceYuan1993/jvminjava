package bruce.jvminjava.rtda.heap;

public class MethodDescriptorParser {
    private String raw;
    private int offset;
    private MethodDescriptor parsed;

    MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
        return this.parsed;
    }

    private void startParams() {
        if (readChar() != '(') {
            causeException();
        }
    }

    private void endParams() {
        if (readChar() != ')') {
            causeException();
        }
    }

    private void finish() {
        if (offset != raw.length()) {
            causeException();
        }
    }

    private char readChar() {
        char b = raw.charAt(offset);
        offset++;
        return b;
    }

    private void unreadChar() {
        offset--;
    }

    private void causeException() {
        throw new RuntimeException("BAD descriptor: " + raw);
    }

    private void parseParamTypes() {
        while (true) {
            String t = parseFieldType();
            if (!"".equals(t)) {
                parsed.addParameterType(t);
            } else {
                break;
            }
        }
    }

    private void parseReturnType() {
        if (readChar() == 'V') {
            parsed.setReturnType("V");
            return;
        }

        unreadChar();
        String t = parseFieldType();
        if (!"".equals(t)) {
            parsed.setReturnType(t);
            return;
        }

        causeException();
    }

    String parseFieldType() {
        switch (readChar()) {
        case 'B':
            return "B";
        case 'C':
            return "C";
        case 'D':
            return "D";
        case 'F':
            return "F";
        case 'I':
            return "I";
        case 'J':
            return "J";
        case 'S':
            return "S";
        case 'Z':
            return "Z";
        case 'L':
            return parseObjectType();
        case '[':
            return parseArrayType();
        default:
            unreadChar();
            return "";
        }
    }

    private String parseObjectType() {
        String unread = raw.substring(offset);
        int semicolonIndex = unread.indexOf(";");
        if (semicolonIndex == -1) {
            causeException();
            return "";
        } else {
            int objStart = offset - 1;
            int objEnd = offset + semicolonIndex + 1;
            offset = objEnd;
            String descriptor = raw.substring(objStart, objEnd);
            return descriptor;
        }
    }

    private String parseArrayType() {
        int arrStart = offset - 1;
        parseFieldType();
        int arrEnd = offset;
        String descriptor = raw.substring(arrStart, arrEnd);
        return descriptor;
    }

}
