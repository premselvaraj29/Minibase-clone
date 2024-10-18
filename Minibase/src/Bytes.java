
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Bytes {
    public final static byte[] EMPTY_BYTES = new byte[0];
    public final static String HEX_TMP = "0123456789ABCDEF";


    public static byte[] toBytes(byte b) {
        return new byte[]{b};
    }

    public static byte[] toBytes(String s) throws IOException {
        if (s == null) return new byte[0];
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] toBytes(int x) {
        byte[] result = new byte[4];
        result[3] = (byte) (x & 0xFF);
        result[2] = (byte) ((x >> 8) & 0xFF);
        result[1] = (byte) ((x >> 16) & 0xFF);
        result[0] = (byte) ((x >> 24) & 0xFF);
        return result;
    }

    public static byte[] toBytes(long x) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            int j = (7 - i) << 3;
            result[i] = (byte) ((x >> j) & 0xFF);
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        return toHex(buf, 0, buf.length);
    }

    public static String toHex(byte[] buf, int offset, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + len; i++) {
            int x = buf[i];
            if (x > 32 && x < 127) {
                sb.append((char) x);
            } else {
                sb.append("\\x").append(HEX_TMP.charAt((x >> 4) & 0x0F)).append(HEX_TMP.charAt(x & 0x0F));
            }
        }
        return sb.toString();
    }

    public static byte[] toBytes(byte[] a, byte[] b) {
        if (a == null) return b;
        if (b == null) return a;
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static int toInt(byte[] a) {
        return (a[0] << 24) & 0xFF000000 | (a[1] << 16) & 0x00FF0000 | (a[2] << 8) & 0x0000FF00
                | (a[3] << 0) & 0x000000FF;
    }

    public static long toLong(byte[] a) {
        long x = 0;
        for (int i = 0; i < 8; i++) {
            int j = (7 - i) << 3;
            x |= ((0xFFL << j) & ((long) a[i] << j));
        }
        return x;
    }


}
