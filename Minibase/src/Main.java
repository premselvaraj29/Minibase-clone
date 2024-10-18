
public class Main {
    public static void main(String[] args) {
        int number = 16909060; // Example long number
        byte[] bytes = Bytes.toBytes(number);
        for (Byte b : bytes) {
            System.out.println(b);
        }
    }
}