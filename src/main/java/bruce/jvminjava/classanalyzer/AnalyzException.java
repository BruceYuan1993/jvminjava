package bruce.jvminjava.classanalyzer;

/**
 * Created by bruceyuan on 17-9-16.
 */
public class AnalyzException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AnalyzException(String message) {
        super(message);
    }

    public AnalyzException(Throwable cause) {
        super(cause);
    }

    public AnalyzException(String message, Throwable cause) {
        super(message, cause);
    }
}
