import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  public void run() {
    Scanner scanner = new Scanner(System.in);

    int h = scanner.nextInt();
    int w = scanner.nextInt();

    int[] nums = new int[26];
    for (int i = 0 ; i < h ; i ++) {
      String a = scanner.next();
      for (int j = 0 ; j < w ; j ++) {
        nums[(int)a.charAt(j) - 97] ++;
      }
    }

    if (h % 2 == 0 && w % 2 == 0) {
      boolean res = true;
      for (int c = 0 ; c < 26 ; c ++) {
        if (nums[c] % 4 != 0) {
          res = false;
          break;
        }
      }
      System.out.println((res) ? "Yes" : "No");
    } else if ((h % 2 == 1 && w % 2 == 0) || (h % 2 == 0 && w % 2 == 1)) {
      boolean res = true;
      int count = 0;
      for (int c = 0 ; c < 26 ; c ++) {
        if (nums[c] % 2 != 0) {
          res = false;
          break;
        }
        if (nums[c] % 4 == 2) {
          count ++;
        }
      }
      if (res) {
        if ((w % 2 == 0 && count <= w / 2) || (h % 2 == 0 && count <= h / 2)) {
          System.out.println("Yes");
        } else {
          System.out.println("No");
        }
      } else {
        System.out.println("No");
      }
    } else {
      int[] mods = new int[26];
      for (int c = 0 ; c < 26 ; c ++) {
        mods[nums[c] % 4] ++;
      }
      if (mods[2] <= (h + w - 2) / 2 && mods[1] + mods[3] <= 1) {
        System.out.println("Yes");
      } else {
        System.out.println("No");
      }
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
