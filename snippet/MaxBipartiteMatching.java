import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  // ◆ 入力
  // ・an … 独立集合 A の頂点数
  // ・bn … 独立集合 B の頂点数
  // ・paths … 独立集合 A の各頂点から伸びる逆側頂点のリストから成る配列
  // ◆ 出力
  // ・parts … 独立集合 B の各頂点とマッチングした独立集合 A の頂点 (なければ -1) から成る配列 
  public void maxBipartiteMatching(int an, int bn, List<Integer>[] paths) {
    int[] parts = new int[bn];
    Arrays.fill(parts, -1);
    for (int a = 0 ; a < an ; a ++) {
      boolean[] visited = new boolean[bn];
      search(a, paths, parts, visited);
    }
    return;
  }

  private boolean search(int a, List<Integer>[] paths, int[] parts, boolean[] visited) {
    for (int b : paths[a]) {
      if (visited[b]) {
        continue;
      }
      visited[b] = true;
      if (parts[b] == -1 || search(parts[b], paths, parts, visited)) {
        parts[b] = a;
        return true;
      }
    }
    return false;
  }
 
}