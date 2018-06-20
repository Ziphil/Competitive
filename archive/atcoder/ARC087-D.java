import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  @SuppressWarnings("unchecked") 
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);
  
    String s = scanner.next();
    int n = s.length();
    int targetX = scanner.nextInt();
    int targetY = scanner.nextInt();
 
    List<Integer> xx = new ArrayList();
    List<Integer> yy = new ArrayList();
 
    int current = 0;
    int mode = 0;
    for (int i = 0 ; i < n ; i ++) {
      if (s.charAt(i) == 'F') {
        current ++;
      } else {
        if (mode == 0) {
          targetX -= current;
        } else {
          if (mode % 2 == 0) {
            xx.add(current);
          } else {
            yy.add(current);
          }
        }
        current = 0;
        mode ++;
      }
    }
    if (mode == 0) {
      targetX -= current;
    } else {
      if (mode % 2 == 0) {
        xx.add(current);
      } else {
        yy.add(current);
      }
    }
 
    if (check(n, xx, targetX) && check(n, yy, targetY)) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
  }
 
  private boolean check(int n, List<Integer> xx, int targetX) {
    boolean[] poss = new boolean[2 * n + 1];
    poss[n] = true;
    for (int i = 0 ; i < xx.size() ; i ++) {
      int x = xx.get(i);
      boolean[] newPoss = new boolean[2 * n + 1];
      for (int j = 0 ; j <= 2 * n ; j ++) {
        if (poss[j]) {
          newPoss[j + x] = true;
          newPoss[j - x] = true;
        }
      }
      poss = newPoss;
    }
    if (n + targetX >= 0 && n + targetX <= 2 * n) {
      return poss[n + targetX];
    } else {
      return false;
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
 
    private boolean isPrintableChar(int codePoint) {
      return codePoint >= 33 && codePoint <= 126;
    }
 
  }
 
}
