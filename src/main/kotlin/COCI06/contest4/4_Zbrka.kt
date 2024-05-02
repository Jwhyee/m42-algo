package COCI06.contest4

import java.util.StringTokenizer

/**
 * ### 문제 설명
 * - 1과 N 사이의 각 정수가 단 한 번만 나타나는 수열 N을 생각해보자.
 * - 수열의 한 쌍(pair)의 수는 수열의 앞에 오는 수가 뒤에 오는 수보다 크면 혼동될 수 있다.
 * - 예를 들어, (1, 4, 3, 2)가 있으면, (4, 3), (4, 2), (3, 2)는 혼동되는 쌍이다.
 *
 * ### 입력
 * - 첫 줄에 두 개의 정수 1 <= N <= 1000과 0 <= C <= 10000이 주어진다.
 *
 * ### 출력
 * - 혼동이 정확히 C의 길이를 가진 N의 수열을 계산해 출력하라.
 *
 * ### 예제 2
 * - 4 3 -> 6
 * - 1, 2, 3, 4 -> 0    /  2, 1, 3, 4 -> 1
 * - 1, 2, 4, 3 -> 1    /  2, 1, 4, 3 -> 2
 * - 1, 3, 2, 4 -> 1    /  2, 3, 1, 4 -> 2
 * - 1, 3, 4, 2 -> 2    /  2, 3, 4, 1 -> [3]
 * - 1, 4, 2, 3 -> 2    /  2, 4, 1, 3 -> [3]
 * - 1, 4, 3, 2 -> [3]  /  2, 4, 3, 1 -> 4
 * >
 * - 3, 1, 2, 4 -> 1    /  4, 1, 2, 3 -> [3]
 * - 3, 1, 4, 2 -> [3]  /  4, 1, 3, 2 -> 4
 * - 3, 2, 1, 4 -> 2    /  4, 2, 1, 3 -> 4
 * - 3, 2, 4, 1 -> [3]  /  4, 2, 3, 1 -> 4
 * - 3, 4, 1, 2 -> 2    /  4, 3, 1, 2 -> 5
 * - 3, 4, 2, 1 -> 2    /  4, 3, 2, 1 -> 6
 * >
 * - 맨 앞이 1일 때 무조건 나올 수 있는 경우 :
 * - 맨 앞이 2일 때 무조건 나올 수 있는 경우 : (2, 1)
 * - 맨 앞이 3일 때 무조건 나올 수 있는 경우 : (3, 1), (3, 2)
 * - 맨 앞이 4일 때 무조건 나올 수 있는 경우 : (4, 1), (4, 2), (4, 3)
 * - > 즉, 맨 앞의 오는 수의 N-1까지가 자신보다 작은 수가 된다.
 *
 * |      N      |   0  |   1  |   2  |   3  |   4  |   5  |   6  |
 * |:------------:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|
 * |      4       |  0   |  0   |  0   |  0   |  0   |  0   |  0  |
 * |  4(맨 앞이 1)  |  1   |  2   |  2   |  1   |  0   |  0   |  0  |
 * |  4(맨 앞이 2)  |  0   |  1   |  2   |  2   |  1   |  0   |  0  |
 * |  4(맨 앞이 3)  |  0   |  0   |  1   |  2   |  2   |  1   |  0  |
 * |  4(맨 앞이 4)  |  0   |  0   |  0   |  1   |  2   |  2   |  1  |
 * |       -      |   -  |   -  |   -  |   -  |   -  |   -  |   -  |
 * |       -      |   1  |   3  |   5  |   6  |   5  |   3  |   1  |
 *
 * - 맨 앞이 1일 때, 0개 나올 수 있는 수 -> 1개
 *
 * */
private const val MOD = 1_000_000_007
fun main() = with(System.`in`.bufferedReader()) {
   val (n, c) = StringTokenizer(readLine()).run {
      (nextToken().toIntOrNull() ?: -1) to (nextToken().toIntOrNull() ?: -1)
   }

   if(n == -1 || c == -1 || n !in 1..1000 || c !in 0..10000) error("Invalid input")

   val dp = Array(n + 1) { IntArray(c + 1) }
   dp[1][0] = 1

   for (i in 2..n) {
      for (j in 0..<i) {
         for (k in 0..<c + 1) {
            if (k + j > c) continue
            dp[i][k + j] += dp[i - 1][k]
            dp[i][k + j] %= MOD
            dp.forEach { arr ->
               println(arr.joinToString(", "))
            }
            println()
         }
      }
   }


   println(dp[n][c])

}