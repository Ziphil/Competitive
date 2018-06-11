import java.io.*;
import java.util.*;


public class Main implements Runnable {

  private static final int[] DX = new int[]{1, 0, -1, 0};
  private static final int[] DY = new int[]{0, 1, 0, -1};
 
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);
    
    while (true) {
      int w = scanner.nextInt();
      int h = scanner.nextInt();
      if (w == 0 && h == 0) {
        break;
      }
      
      int[][] board = new int[w][h];
      int fx = 0;
      int fy = 0;
      for (int y = 0 ; y < h ; y ++) {
        for (int x = 0 ; x < w ; x ++) {
          board[x][y] = scanner.nextInt();
          if (board[x][y] == 2) {
            fx = x;
            fy = y;
          }
        }
      }
      int res = dfs(board, 0, fx, fy);
      if (res == Integer.MAX_VALUE) {
        res = -1;
      }
      System.out.println(res);
    }
  }
  
  public int dfs(int[][] board, int round, int x, int y) {
    if (round >= 10) {
      return Integer.MAX_VALUE;
    }
    int res = Integer.MAX_VALUE;
    for (int l = 0 ; l < 4 ; l ++) {
      int k = 1;
      int cres = 0;
      while (true) {
        int nx = x + DX[l] * k;
        int ny = y + DY[l] * k;
        if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) {
          cres = Integer.MAX_VALUE;
          break;
        }
        if (board[nx][ny] == 1) {
          if (k == 1) {
            cres = Integer.MAX_VALUE;
            break;
          }
          int[][] nboard = deepCopyOf(board);
          nboard[nx][ny] = 0;
          cres = dfs(nboard, round + 1, nx - DX[l], ny - DY[l]);
          break;
        } else if (board[nx][ny] == 3) {
          cres = round + 1;
          break;
        }
        k ++;
      }
      if (cres < res) {
        res = cres;
      }
    }
    return res;
  }
  
  public int[][] deepCopyOf(int[][] array) {
    int[][] res = new int[array.length][array[0].length];
    for (int i = 0 ; i < array.length ; i ++) {
      res[i] = Arrays.copyOf(array[i], array[i].length);
    }
    return res;
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
