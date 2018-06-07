import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {
 
  public static final int MOD = 1000000007;

  public void run() {
    long x = div(2, 5);
    long y = pow(2, 5);
    long[][] fact = calcFact(100);
    long comb = comb(fact, 10, 5);
  }

  public long div(long a, long b) {
    return a * pow(b, MOD - 2) % MOD;
  }

  public long pow(long a, long b) {
    if (b == 0) {
      return 1;
    } else if (b % 2 == 0) {
      long d = pow(a, b / 2);
      return (d * d) % MOD;
    } else {
      long d = pow(a, b - 1);
      return (a * d) % MOD;
    }
  }

  // ◆ 入力
  // ・n … 上限
  // ◆ 出力
  // ・＊ … 0 から n までの階乗とその逆元から成る配列
  public long[][] calcFact(int n) {
    long[] fact = new long[n];
    long[] invfact = new long[n];
    fact[0] = 1;
    for (int i = 1 ; i < n ; i ++) {
      fact[i] = (fact[i - 1] * i) % MOD;
    }
    invfact[n - 1] = pow(fact[n - 1], MOD - 2);
    for (int i = n - 2 ; i >= 0 ; i --) {
      invfact[i] = invfact[i + 1] * (i + 1) % MOD;
    }
    return new long[][]{fact, invfact};
  }

  public long comb(long[][] fact, int n, int r) {
    long res = fact[0][n];
    res *= fact[1][n - r];
    res %= MOD;
    res *= fact[1][r];
    res %= MOD;
    return res;
  }
 
}
