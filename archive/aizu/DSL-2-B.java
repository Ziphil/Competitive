import java.io.*;
import java.util.*;
import java.util.function.*;
 
 
public class Main implements Runnable {

  @SuppressWarnings("unchecked") 
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);

    int n = scanner.nextInt();
    int q = scanner.nextInt();

    SegmentTree tree = new SegmentTree(n, 0, (a1, a2) -> a1 + a2);
    StringBuilder output = new StringBuilder();

    for (int i = 0 ; i < q ; i ++) {
      int c = scanner.nextInt();
      int x = scanner.nextInt();
      int y = scanner.nextInt();
      if (c == 0) {
        tree.update(x - 1, tree.get(x - 1) + y);
      } else {
        int res = tree.find(x - 1, y);
        output.append(res).append("\n");
      }
    }
    System.out.print(output);
  }

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

  public static void main(String[] args) {
    Main main = new Main();
    main.run();
  }
 
  public static class BetterScanner {
 
    private InputStream stream;
    private byte[] buffer = new byte[1024];
    private int pointer = 0;
    private int bufferLength = 0;
 
    public BetterScanner(InputStream stream) {
      this.stream = stream;
    }
 
    private boolean updateBuffer() {
      if (pointer >= bufferLength) {
        pointer = 0;
        try {
          bufferLength = stream.read(buffer);
        } catch (IOException exception) {
          exception.printStackTrace();
        }
        return bufferLength > 0;
      } else {
        return true;
      }
    }
 
    private int read() {
      if (updateBuffer()) {
        return buffer[pointer ++];
      } else {
        return -1;
      }
    }
 
    public boolean hasNext() {
      skipUnprintable();
      return updateBuffer();
    }
 
    private void skipUnprintable() { 
      while (updateBuffer() && !isPrintableChar(buffer[pointer])) {
        pointer ++;
      }
    }
 
    public String next() {
      if (hasNext()) {
        StringBuilder builder = new StringBuilder();
        int codePoint = read();
        while (isPrintableChar(codePoint)) {
          builder.appendCodePoint(codePoint);
          codePoint = read();
        }
        return builder.toString();
      } else {
        throw new NoSuchElementException();
      }
    }
 
    public long nextLong() {
      if (hasNext()) {
        long number = 0;
        boolean minus = false;
        int codePoint = read();
        if (codePoint == '-') {
          minus = true;
          codePoint = read();
        }
        if (codePoint >= '0' && codePoint <= '9') {
          while (true) {
            if (codePoint >= '0' && codePoint <= '9') {
              number *= 10;
              number += codePoint - '0';
            } else if (codePoint < 0 || !isPrintableChar(codePoint)) {
              return (minus) ? -number : number;
            } else {
              throw new NumberFormatException();
            }
            codePoint = read();
          }
        } else {
          throw new NumberFormatException();
        }
      } else {
        throw new NoSuchElementException();
      }
    }
 
    public int nextInt() {
      long number = nextLong();
      if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
        return (int)number;
      } else {
        throw new NumberFormatException();
      }
    }

    public long[] nextLongArray(int n) {
      long[] array = new long[n];
      for (int i = 0 ; i < n ; i ++) {
        array[i] = nextLong();
      }
      return array;
    }

    public int[] nextIntArray(int n) {
      int[] array = new int[n];
      for (int i = 0 ; i < n ; i ++) {
        array[i] = nextInt();
      }
      return array;
    }
 
    private boolean isPrintableChar(int codePoint) {
      return codePoint >= 33 && codePoint <= 126;
    }
 
  }
 
}
