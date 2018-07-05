import java.io.*;
import java.util.*;
import java.util.function.*;
 
 
public class Main implements Runnable {

  public static class SegmentTree {

    private int size = 1;
    private int[] array;
    private int first;
    private IntBinaryOperator operator;

    public SegmentTree(int n, int first, IntBinaryOperator operator) {
      this.first = first;
      this.operator = operator;
      while (size < n) {
        size *= 2;
      }
      array = new int[2 * size - 1];
      Arrays.fill(array, first);
    }

    public void update(int i, int data) {
      i += size - 1;
      array[i] = data;
      while (i > 0) {
        i = (i - 1) / 2;
        array[i] = operator.applyAsInt(array[i * 2 + 1], array[i * 2 + 2]);
      }
    }

    public int find(int a, int b) {
      return find(a, b, 0, 0, size);
    }

    public int find(int a, int b, int i, int l, int r) {
      if (a >= r || b <= l) {
        return first;
      } else if (a <= l && b >= r) {
        return array[i];
      } else {
        int m = (l + r) / 2;
        return operator.applyAsInt(find(a, b, i * 2 + 1, l, m), find(a, b, i * 2 + 2, m, r));
      }
    }

    public int get(int i) {
      return array[i + size - 1];
    }

  }

}