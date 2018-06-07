import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  public void run() {
    int n = 10;
    UnionFind tree = new UnionFind(n);
    tree.unite(1, 2);
    if (tree.isConnected(1, 2)) {
      //
      // 何らかの処理
      //
    }
  }

  public static class UnionFind {
    
    private int[] parents;
    private int[] ranks;

    public UnionFind(int max) {
      parents = new int[max];
      ranks = new int[max];
      for (int i = 0 ; i < max; i ++) {
        parents[i] = i;
      }
    }

    public int find(int i) {
      int parent = parents[i];
      if (i == parent) {
        return i;
      } else {
        parents[i] = find(parent);
        return parents[i];
      }
    }

    public void unite(int i, int j) {
      int iRoot = find(i);
      int jRoot = find(j);
      if (iRoot != jRoot) {
        if (ranks[iRoot] > ranks[jRoot]) {
          parents[jRoot] = iRoot;
        } else if (ranks[jRoot] > ranks[iRoot]) {
          parents[iRoot] = jRoot;
        } else {
          parents[jRoot] = iRoot;
          ranks[iRoot] ++;
        }
      }
    }

    public boolean isConnected(int i, int j) {
      return find(i) == find(j);
    }
  }
 
}
