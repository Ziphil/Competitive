import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {

  public void run() {
    int[] array = new int[]{0, 1, 2, 3, 4};
    do {
      //
      // 配列を用いた処理
      //
    } while (nextPermutation(array));
  }

  // ◆ 入力
  // ・array … 現在の順列
  // ◆ 出力
  // ・＊ … 次の順列が存在するか
  private static boolean nextPermutation(int[] array) {
    for (int i = array.length - 1 ; i > 0 ; i --) {
      if (array[i - 1] < array[i]) {
        int j = find(array, array[i - 1], i, array.length - 1);
        int temp = array[j];
        array[j] = array[i - 1];
        array[i - 1] = temp;
        Arrays.sort(array, i, array.length);
        return true;
      }
    }
    return false;
  }

  private static int find(int[] array, int dest, int f, int l) {
    if (f == l) {
      return f;
    }
    int m = (f + l + 1) / 2;
    return (array[m] <= dest) ? find(array, dest, f, m - 1) : find(array, dest, m, l);
  }
 
}
