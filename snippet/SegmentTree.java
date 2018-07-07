import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  public static class SegmentTree {

    private int size = 1;
    private long[] array;
    private long unit;
    private Operator operator;

    public SegmentTree(int n, long unit, Operator operator) {
      this.unit = unit;
      this.operator = operator;
      while (size < n) {
        size *= 2;
      }
      array = new long[2 * size - 1];
      Arrays.fill(array, unit);
    }

    public long get(int i) {
      return array[i + size - 1];
    }

    public void set(int i, long data) {
      i += size - 1;
      array[i] = data;
      while (i > 0) {
        i = (i - 1) / 2;
        array[i] = operator.operate(array[i * 2 + 1], array[i * 2 + 2]);
      }
    }

    public long find(int a, int b) {
      return find(a, b, 0, 0, size);
    }

    public long find(int a, int b, int i, int l, int r) {
      if (a >= r || b <= l) {
        return unit;
      } else if (a <= l && b >= r) {
        return array[i];
      } else {
        int m = (l + r) / 2;
        return operator.operate(find(a, b, i * 2 + 1, l, m), find(a, b, i * 2 + 2, m, r));
      }
    }

  }

  @FunctionalInterface
  public static interface Operator {

    public long operate(long left, long right);

  }

}