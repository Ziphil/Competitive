import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  public void run() {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int[] aa = new int[n];
    int[] nums = new int[5];
    for (int i = 0 ; i < n ; i ++) {
      aa[i] = scanner.nextInt();
      nums[aa[i]] ++;
    }

    int res = 0;
    for (int i = 0 ; i < n ; i ++) {
      if (nums[1] == 0 && nums[2] == 0 && nums[3] == 0 && nums[4] == 0) {
        break;
      }
      int a = aa[i];
      if (nums[a] == 0) {
        continue;
      }
      nums[a] --;
      res ++;
      if (a == 1) {
        if (nums[3] > 0) {
          nums[3] --;
        } else if (nums[2] > 0) {
          nums[2] --;
          if (nums[1] > 0) {
            nums[1] --;
          }
        } else if (nums[1] > 0) {
          nums[1] -= Math.min(3, nums[1]);
        }
      } else if (a == 2) {
        if (nums[2] > 0) {
          nums[2] --;
        } else if (nums[1] > 0) {
          nums[1] -= Math.min(2, nums[1]);
        }
      } else if (a == 3) {
        if (nums[1] > 0) {
          nums[1] --;
        }
      }
    }

    System.out.println(res);
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
