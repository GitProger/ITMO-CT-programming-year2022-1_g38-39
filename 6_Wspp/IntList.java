import java.util.Arrays;

public class IntList {
    private int[] array = new int[0];
    private int length = 0;

    public IntList(int a[]) {
        array = a;
        length = a.length;
    }

    public IntList() {
    }

    public int size() {
        return length;
    }

    public void reserve(int sz) {
        int ln = array.length;
        if (ln < sz) {
            array = Arrays.copyOf(array, sz + sz / 2 + 1);
        }
    }

    public void resize(int sz) {
        reserve(sz);
        length = sz;
    }

    public boolean add(int e) {
        if (length >= array.length) {
            reserve(length + 1);
        }
        array[length] = e;
        length++;
        return true;
    }

    public int get(int i) {
        return array[i];
    }

    public void set(int i, int v) {
        array[i] = v;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(array[i]);
            if (i != length - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
