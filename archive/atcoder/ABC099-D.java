import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  @SuppressWarnings("unchecked") 
  public void run() {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int k = scanner.nextInt();

    int[][] dd = new int[k][k];
    int[][] cc = new int[n][n];
    for (int i = 0 ; i < k ; i ++) {
      for (int j = 0 ; j < k ; j ++) {
        dd[i][j] = scanner.nextInt();
      }
    }
    for (int i = 0 ; i < n ; i ++) {
      for (int j = 0 ; j < n ; j ++) {
        cc[i][j] = scanner.nextInt();
      }
    }

    int[][] nums = new int[3][k];
    for (int s = 0 ; s < 3 ; s ++) {
      for (int c = 0 ; c < k ; c ++) {
        int num = 0;
        for (int i = 0 ; i < n ; i ++) {
          for (int j = 0 ; j < n ; j ++) {
            if ((i + j) % 3 == s) {
              num += dd[cc[i][j] - 1][c];
            }
          }
        }
        nums[s][c] = num;
      }
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0 ; i < k ; i ++) {
      for (int j = 0 ; j < k ; j ++) {
        for (int l = 0 ; l < k ; l ++) {
          if (i != j && j != l && l != i) {
            int num = nums[0][i] + nums[1][j] + nums[2][l];
            if (num < min) {
              min = num;
            }
          }
        }
      }
    }

    System.out.println(min);
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
