import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  // ◆ 入力
  // ・n … 頂点数
  // ・costs … 第 1 成分から第 2 成分への辺の重み (辺がない場合は Integer.MAX_VALUE)
  // ◆ 出力
  // ・dd … 最短距離
  public void warshallFloyd(int n, int[][] costs) {
    int[][] dd = new int[n][n];
    for (int i = 0 ; i < n ; i ++) {
      for (int j = 0 ; j < n ; j ++) {
        dd[i][j] = costs[i][j];
      }
    }
    for (int k = 0 ; k < n ; k ++) {
      for (int i = 0 ; i < n ; i ++) {
        for (int j = 0 ; j < n ; j ++) {
          if (dd[i][k] < Integer.MAX_VALUE && dd[k][j] < Integer.MAX_VALUE && dd[i][j] > dd[i][k] + dd[k][j]) {
            dd[i][j] = dd[i][k] + dd[k][j];
          }
        }
      }
    }
    return;
  }
 
}
