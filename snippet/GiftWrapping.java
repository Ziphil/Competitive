import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  // ◆ 入力
  // ・n … 頂点数
  // ・xx … x 座標の配列
  // ・yy … y 座標の配列
  // ◆ 出力
  // ・outers … 凸包上の点 (時計回り)
  public void giftWrapping(int n, int[] xx, int[] yy) {
    int a = 0;
    int minY = Integer.MAX_VALUE;
    int minX = Integer.MAX_VALUE;
    for (int i = 0 ; i < n ; i ++) {
      if (yy[i] < minY || (yy[i] == minY && xx[i] < minX)) {
        minY = y;
        minX = x;
        a = i;
      }
    }
    List<Integer> outers = new ArrayList(n);
    int b = 0;
    do {
      outers.add(a);
      b = 0;
      for (int c = 1 ; c < n ; c ++) {
        if (b == a) {
          b = c;
        } else {
          long v = (long)(xx[a] - xx[b]) * (yy[a] - yy[c]) - (long)(xx[a] - xx[c]) * (yy[a] - yy[b]);
          long ab = (long)(xx[a] - xx[b]) * (xx[a] - xx[b]) + (long)(yy[a] - yy[b]) * (yy[a] - yy[b]);
          long ac = (long)(xx[a] - xx[c]) * (xx[a] - xx[c]) + (long)(yy[a] - yy[c]) * (yy[a] - yy[c]);
          if (v > 0 || (v == 0 && ac > ab)) {
            b = c;
          }
        }
      }
      a = b;
    } while (a != outers.get(0)); 
    return;
  }
 
}
