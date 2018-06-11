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
    Queue<Vertex> queue = new PriorityQueue((v1, v2) -> v1.dist - v2.dist);
    for (int i = 0 ; i < n ; i ++) {
      queue.offer(new Vertex(i, dd[i]));
    }
    while (!queue.isEmpty()) {
      Vertex v = queue.poll();
      int c = v.id;
      if (dd[c] < v.dist) {
        continue;
      }
      for (int j = 0 ; j < paths[c].size() ; j ++) {
        int b = paths[c].get(j);
        int cost = costs[c].get(j);
        if (dd[c] < Integer.MAX_VALUE && dd[b] > dd[c] + cost) {
          dd[b] = dd[c] + cost;
          prevs[b] = c;
          queue.offer(new Vertex(b, dd[b]));
        }
      }
    }
    return;
  }

  public static class Vertex {

    public int id;
    public int dist;

    public Vertex(int id, int dist) {
      this.id = id;
      this.dist = dist;
    }

  }
 
}
