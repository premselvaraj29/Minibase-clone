import java.io.IOException;
import java.util.Comparator;

public class KeyValue implements Comparable<KeyValue> {
    public static final int RAW_KEY_LEN_SIZE = 4;
    public static final int VAL_LEN_SIZE = 4;

    @Override
    public int compareTo(KeyValue kv) {
        return 0;
    }
}
