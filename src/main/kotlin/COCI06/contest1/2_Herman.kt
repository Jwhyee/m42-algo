package COCI06.contest1

import kotlin.math.PI
import kotlin.math.pow

/**
 * investigated a non-Euclidean geometry : 비유클리드 기하학
 * taxicab geometry : 택시 기하학
 * [D(T_1,T_2) = |x_1 - x_2| + |y_1 - y_2|]
 * > [링크](https://dmoj.ca/problem/coci06c1p2)
 * */
fun main() = with(System.`in`.bufferedReader()) {
   val r = readLine().toDoubleOrNull() ?: throw (IllegalArgumentException("ㅅㄱ"))

   val result = r.pow(2.0) * PI

   println(String.format("%.6f", result))
   println(String.format("%.6f", 2 * r * r))
}