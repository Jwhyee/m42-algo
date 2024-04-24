package COCI06.contest3

import java.util.StringTokenizer

/**
 * ### 문제 설명
 * - 멀리 떨어진 땅에서 자전거 경주가 열렸다.
 * - 거기에는 N개의 마을이 있고, 1 ~ N까지 번호가 붙어있다.
 * - 각 마을 사이에 M개의 길이 있다.
 * - 이 경주는 1번 마을에서 시작하고, 2번 마을에서 끝이 난다.
 * - 얼마나 많은 다른 방법으로 갈 수 있는지 구해라.
 * - 동일한 도로를 이용하지 않을 경우 다른 것으로 간주한다.
 * ### 제한 사항
 * - 첫 줄에 2개의 정수 N, M이 주어진다.(1 <= N <= 10,000 / 1 <= M <= 100,000)
 * - M개의 줄에 2가지 다른 정수 A, B가 주어지고, 이는 마을 사이의 길을 나타낸다.
 * - 첫 줄에 고유 경로 수를 출력하되, 만약 9자리를 넘을 경우 마지막 9자리만 출력해라.
 * - 경로가 무한히 많을 경우 inf를 출력하라.
 * - [링크](https://dmoj.ca/problem/coci06c3p5)
* */
private var cnt = 0

fun main() = with(System.`in`.bufferedReader()) {
   val (n, m) = StringTokenizer(readLine()).run {
      (nextToken().toIntOrNull() ?: 0) to (nextToken().toIntOrNull() ?: 0)
   }

   // N, M 밸리데이션
   if(n == 0 || n !in 1..10_000 || m == 0 || m !in 1..100_000) error("Invalid N, M")

   val graph = Array(n + 1) { mutableListOf<Int>() }

   // 값을 입력 받고, 그래프에 추가
   repeat(m) {
      val (a, b) = StringTokenizer(readLine()).run {
         (nextToken().toIntOrNull() ?: 0) to (nextToken().toIntOrNull() ?: 0)
      }
      if(a == 0 || b == 0) error("Invalid A, B")

      graph[a] += b
   }

   // inf 출력을 위한 try-catch
   try {
      backTracking(1, graph)
      val result = cnt.toString()
      if (result.length > 9) {
         println(result.substring(result.length - 9, result.length))
      } else {
         println(result)
      }
   } catch (e: StackOverflowError) {
      println("inf")
   }
}

private fun backTracking(cur: Int, graph: Array<MutableList<Int>>) {
   if (cur == 2) {
      cnt++
      return
   }
   for (route in graph[cur]) {
      backTracking(route, graph)
   }
}