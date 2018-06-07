import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  // ◆ 入力
  // ・n … 頂点数
  // ・paths … 各頂点から伸びる辺の逆側頂点のリストから成る配列
  // ・costs … 各頂点から伸びる辺の重みのリストから成る配列
  // ・first … 始点
  // ◆ 出力
  // ・dd … 最短距離
  // ・prevs … 最短距離を通るときの直前の頂点
  public void dijkstra(int n, List<Integer>[] paths, List<Integer>[] costs, int first) {
    int[] dd = new int[n];
    int[] prevs = new int[n];
    for (int i = 0 ; i < n ; i ++) {
      dd[i] = (i == first) ? 0 : Integer.MAX_VALUE;
      prevs[i] = -1;
    }
    Queue<Integer> queue = new PriorityQueue((n1, n2) -> dd[n1.intValue()] - dd[n2.intValue()]);
    for (int i = 0 ; i < n ; i ++) {
      queue.offer(i);
    }
    while (!queue.isEmpty()) {
      int c = queue.poll();
      for (int j = 0 ; j < paths[c].size() ; j ++) {
        int b = paths[c].get(j);
        int cost = costs[c].get(j);
        if (dd[b] > dd[c] + cost) {
          dd[b] = dd[c] + cost;
          prevs[b] = c;
          if (queue.remove(b)) {
            queue.offer(b);
          }
        }
      }
    }
    return;
  }
 
}
