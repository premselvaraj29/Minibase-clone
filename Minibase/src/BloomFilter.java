public class BloomFilter {
    private final int k; //Number of hash functions to use
    private final int bitsPerKey;
    private byte[] result;
    private int bitLen;

    public BloomFilter(int k, int bitsPerKey) {
        this.k = k;
        this.bitsPerKey = bitsPerKey;
    }


    public byte[] generate(byte[][] keys) {
        assert keys != null;
        this.bitLen = keys.length * bitsPerKey;
        this.bitLen = ((this.bitLen + 7) / 8) << 3;
        this.bitLen = Math.max(this.bitLen, 64);
        this.result = new byte[this.bitLen >> 3];
        for (byte[] key : keys) {
            assert key != null;
            int h = Bytes.hash(key);
            for (int t = 0; t < k; t++) {
                int idx = (h % this.bitLen + this.bitLen) % this.bitLen;
                this.result[idx / 8] |= (byte) (1 << (idx % 8));
                int delta = (h >> 17) | (h << 15);
                h += delta;
            }
        }
        return this.result;
    }


    public boolean contains(byte[] key) {
        assert result != null;
        int h = Bytes.hash(key);
        for (int t = 0; t < k; t++) {
            int idx = (h % bitLen + bitLen) % bitLen;
            if ((result[idx / 8] & (1 << (idx % 8))) == 0) {
                return false;
            }
            int delta = (h >> 17) | (h << 15);
            h += delta;
        }
        return true;
    }

}
