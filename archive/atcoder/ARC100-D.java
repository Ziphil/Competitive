import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  @SuppressWarnings("unchecked") 
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);

    int n = scanner.nextInt();
    long[] aa = scanner.nextLongArray(n);

    long[] sums = new long[n + 1];
    for (int i = 1 ; i <= n ; i ++) {
      sums[i] += aa[i - 1] + sums[i - 1];
    }

    long[] lls = new long[n + 1];
    long[] lrs = new long[n + 1];
    long[] rls = new long[n + 1];
    long[] rrs = new long[n + 1];
    int b;

    b = 1;
    lls[2] = aa[0];
    lrs[2] = aa[1];
    for (int i = 3 ; i <= n - 2 ; i ++) {
      long l = lls[i - 1];
      long r = lrs[i - 1];
      while (true) {
        if (Math.abs(l - (r + aa[i - 1])) > Math.abs((l + aa[b]) - (r - aa[b] + aa[i - 1]))) {
          l += aa[b];
          r -= aa[b];
          b ++;
        } else {
          break;
        }
      }
      lls[i] = l;
      lrs[i] = r + aa[i - 1];
    }

    b = n - 1;
    rls[n - 2] = aa[n - 2];
    rrs[n - 2] = aa[n - 1];
    for (int i = n - 3 ; i >= 2 ; i --) {
      long l = rls[i + 1];
      long r = rrs[i + 1];
      while (true) {
        if (Math.abs((l + aa[i]) - r) > Math.abs((l - aa[b - 1] + aa[i]) - (r + aa[b - 1]))) {
          l -= aa[b - 1];
          r += aa[b - 1];
          b --;
        } else {
          break;
        }
      }
      rls[i] = l + aa[i];
      rrs[i] = r;
    }

    long ans = Long.MAX_VALUE;
    for (int i = 2 ; i <= n - 2 ; i ++) {
      long max = Math.max(Math.max(lls[i], lrs[i]), Math.max(rls[i], rrs[i]));
      long min = Math.min(Math.min(lls[i], lrs[i]), Math.min(rls[i], rrs[i]));
      if (max - min < ans) {
        ans = max - min;
      }
    }
    System.out.println(ans);
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
