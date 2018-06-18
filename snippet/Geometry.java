import java.io.*;
import java.util.*;
 
 
public class Main implements Runnable {
 
  // ◆ 入力
  // ・x0 … 1 つ目の点の x 座標 (点の順番は反時計回り)
  // ・y0 … 1 つ目の点の y 座標
  // ・x1 … 2 つ目の点の x 座標
  // ・y1 … 2 つ目の点の y 座標
  // ◆ 出力
  // ・＊ … 2 点間の距離の 2 乗
  public double distsq(double x0, double y0, double x1, double y1) {
    return (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0);
  }
  
  // ◆ 入力
  // ・x0 … 1 つ目の点の x 座標 (点の順番は反時計回り)
  // ・y0 … 1 つ目の点の y 座標
  // ・x1 … 2 つ目の点の x 座標
  // ・y1 … 2 つ目の点の y 座標
  // ・x2 … 3 つ目の点の x 座標
  // ・y2 … 3 つ目の点の y 座標
  // ◆ 出力
  // ・＊ … 三角形の符号付き面積
  public double area(double x0, double y0, double x1, double y1, double x2, double y2) {
    double xv1 = x1 - x0;
    double yv1 = y1 - y0;
    double xv2 = x2 - x0;
    double yv2 = y2 - y0;
    return (xv1 * yv2 - xv2 * yv1) / 2;
  }
  
  // ◆ 入力
  // ・x0 … 1 つ目のベクトルの x 座標
  // ・y0 … 1 つ目のベクトルの y 座標
  // ・x1 … 2 つ目のベクトルの x 座標
  // ・y1 … 2 つ目のベクトルの y 座標
  // ◆ 出力
  // ・＊ … 内積
  public double innerProd(double x0, double y0, double x1, double y1) {
    return x0 * x1 + y0 * y1;
  }
  
  // ◆ 入力
  // ・x0 … 1 つ目のベクトルの x 座標
  // ・y0 … 1 つ目のベクトルの y 座標
  // ・x1 … 2 つ目のベクトルの x 座標
  // ・y1 … 2 つ目のベクトルの y 座標
  // ◆ 出力
  // ・＊ … 外積
  public double outerProd(double x0, double y0, double x1, double y1) {
    return x0 * y1 - x1 * y0;
  }
 
}
