package COCI06.contest2

import java.util.*


/**
 * ### 문제 설명
 * 총 3개의 수(A, B, C)가 주어진다.
 * 이 수들은 정렬되어 주어지지 않는다.
 * 그러나 우리는 A가 B보단 작고, B는 C보다 작은 것을 안다.
 * 주어진 순서대로 재배치하라.
 * - [문제 링크](https://dmoj.ca/problem/coci06c2p2)
 *
 * ### 제한 사항
 * - 각 수들은 100보다 작거나 같다.
 * */
fun main() = with(System.`in`.bufferedReader()) {
   val numbers = readLine().split(" ").sorted()
   val bw = System.out.bufferedWriter()
   readLine().toCharArray().forEach { c ->
      bw.append(numbers[c - 'A']).append(" ")
   }
   bw.flush()
   bw.close()
   close()
}