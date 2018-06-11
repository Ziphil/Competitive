import java.io.*;
import java.util.*;


public class Main implements Runnable {
 
  public void run() {
    BetterScanner scanner = new BetterScanner(System.in);
    
    String ground = scanner.next();
    Deque<Integer> deque = new ArrayDeque<Integer>();
    Deque<Puddle> puddles = new ArrayDeque<Puddle>();
    
    int total = 0;
    for (int i = 0 ; i < ground.length() ; i ++) {
      char c = ground.charAt(i);
      if (c == '\\') {
        deque.addFirst(i);
      } else if (c == '/') {
        if (deque.isEmpty()) {
          continue;
        }
        int before = deque.removeFirst();
        int len = i - before;
        total += len;
        while (!puddles.isEmpty()) {
          Puddle first = puddles.peekFirst();
          if (before < first.pos) {
            puddles.removeFirst();
            len += first.len;
          } else {
            break;
          }
        }
        puddles.addFirst(new Puddle(before, len));
      }
    }
    
    System.out.println(total);
    System.out.print("" + puddles.size());
    if (!puddles.isEmpty()) {
      System.out.print(" ");
    }
    while (!puddles.isEmpty()) {
      Puddle puddle = puddles.removeLast();
      System.out.print(puddle.len);
      if (!puddles.isEmpty()) {
        System.out.print(" ");
      }
    }
    System.out.println();
  }

  public static class Puddle {

    public int pos;
    public int len;

    public Puddle(int pos, int len) {
      this.pos = pos;
      this.len = len;
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
