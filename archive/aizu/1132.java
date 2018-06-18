import java.io.*;
import java.util.*;


public class Main implements Runnable {
 
  @SuppressWarnings("unchecked")
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);
    
    while (true) {
      int n = scanner.nextInt();
      if (n == 0) {
        break;
      }
      double[] xx = new double[n];
      double[] yy = new double[n];
      for (int i = 0 ; i < n ; i ++) {
        xx[i] = Double.parseDouble(scanner.next());
        yy[i] = Double.parseDouble(scanner.next());
      }
      
      int max = 1;
      for (int i = 0 ; i < n ; i ++) {
        for (int j = i + 1 ; j < n ; j ++) {
          double mx = (xx[i] + xx[j]) / 2;
          double my = (yy[i] + yy[j]) / 2;
          double d = distsq(mx, my, xx[i], yy[i]);
          if (d > 1) {
            continue;
          }
          double f = Math.sqrt((1 - d) / (4 * d));
          double[] cxx = new double[]{mx + (yy[i] - yy[j]) * f, mx - (yy[i] - yy[j]) * f};
          double[] cyy = new double[]{my + (xx[j] - xx[i]) * f, my - (xx[j] - xx[i]) * f};
          for (int s = 0 ; s < 2 ; s ++) {
            int count = 0;
            for (int k = 0 ; k < n ; k ++) {
              if (k == i || k == j) {
                continue;
              }
              if (distsq(cxx[s], cyy[s], xx[k], yy[k]) < 1) {
                count ++;
              }
            }
            count += 2;
            if (count > max) {
              max = count;
            }
          }
        }
      }
      
      System.out.println(max);
    }
  }

  public double distsq(double x0, double y0, double x1, double y1) {
    return (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0);
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
