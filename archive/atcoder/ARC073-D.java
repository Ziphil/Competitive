import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  @SuppressWarnings("unchecked") 
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);

    int n = scanner.nextInt();
    long wMax = scanner.nextLong();

    List<Long>[] vv = new List[4];
    long w0 = 0;
    for (int i = 0 ; i < 4 ; i ++) {
      vv[i] = new ArrayList();
    }
    for (int i = 0 ; i < n ; i ++) {
      long w = scanner.nextLong();
      long v = scanner.nextLong();
      if (i == 0) {
        w0 = w;
      }
      vv[(int)(w - w0)].add(v);
    }

    for (int i = 0 ; i < 4 ; i ++) {
      vv[i].sort((v1, v2) -> (int)Math.signum(v2 - v1));
      for (int j = 1 ; j < vv[i].size() ; j ++) {
        vv[i].set(j, vv[i].get(j - 1) + vv[i].get(j));
      }
    }

    long max = 0;
    for (int i0 = 0 ; i0 <= vv[0].size() ; i0 ++) {
      for (int i1 = 0 ; i1 <= vv[1].size() ; i1 ++) {
        for (int i2 = 0 ; i2 <= vv[2].size() ; i2 ++) {
          long used = i0 * w0 + i1 * (w0 + 1) + i2 * (w0 + 2);
          if (wMax - used < 0) {
            break;
          }
          int i3 = (int)Math.min(vv[3].size(), (wMax - used) / (w0 + 3));
          long v = 0;
          v += (i0 > 0) ? vv[0].get(i0 - 1) : 0;
          v += (i1 > 0) ? vv[1].get(i1 - 1) : 0;
          v += (i2 > 0) ? vv[2].get(i2 - 1) : 0;
          v += (i3 > 0) ? vv[3].get(i3 - 1) : 0;
          if (v > max) {
            max = v;
          }
        }
      }
    }

    System.out.println(max);
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
 
    private boolean isPrintableChar(int codePoint) {
      return codePoint >= 33 && codePoint <= 126;
    }
 
  }
 
}
