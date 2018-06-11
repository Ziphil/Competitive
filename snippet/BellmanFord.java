import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  // ◆ 入力
  // ・n … 頂点数
  // ・m … 辺数
  // ・ss … 各辺の始点のリスト
  // ・tt … 各辺の終点のリスト
  // ・cc … 各辺の重みのリスト
  // ・first … 始点
  // ◆ 出力
  // ・dd … 最短距離
  // ・prevs … 最短距離を通るときの直前の頂点
  // ・neg … 重みが負の閉路が存在するか
  public void bellmanFord(int n, int m, List<Integer> ss, List<Integer> tt, List<Integer> cc, int first) {
    int[] dd = new int[n];
    int[] prevs = new int[n];
    for (int i = 0 ; i < n ; i ++) {
      dd[i] = (i == first) ? 0 : Integer.MAX_VALUE;
    }
    for (int i = 0 ; i < m ; i ++) {
      boolean changed = false;
      for (int j = 0 ; j < ss.size() ; j ++) {
        int s = ss.get(j);
        int t = tt.get(j);
        int c = cc.get(j);
        if (dd[s] < Integer.MAX_VALUE && dd[t] > dd[s] + c) {
          dd[t] = dd[s] + c;
          prevs[t] = s;
          changed = true;
        }
      }
      if (!changed) {
        break;
      }
    }
    boolean neg = false;
    for (int j = 0 ; j < ss.size() ; j ++) {
      int s = ss.get(j);
      int t = tt.get(j);
      int c = cc.get(j);
      if (dd[s] < Integer.MAX_VALUE && dd[t] > dd[s] + c) {
        neg = true;
        break;
      }
    }
    return;
  }

}