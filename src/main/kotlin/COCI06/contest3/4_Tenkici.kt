package COCI06.contest3

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.StringTokenizer

/**
 * ### 문제 설명
 * 미르코는 할아버지의 다락방에서 N개의 2차 세계 대전 탱크 장난감을 발견했다.
 * 그는 즉시 그의 친구 슬라브코를 불러서 놀았다.
 * 밥을 먹고 다시 시작하
 *
 * ### 제한 사항
 * - 나무 보드는 N x N 크기이다.
 * - 각 탱크는 이웃된 4칸으로 한 칸씩 전진할 수 있다.
 * - 같은 행이나 같은 열에 있는 탱크를 쏠 수 있다.
 * - 한 칸에 두 개의 탱크가 함께 존재할 수 없다.
 * - 출력의 순서는 유니크하지 않다.
 * */
private lateinit var map: Array<IntArray>
private val dx = intArrayOf(0, 1, 0, 0, -1)
private val dy = intArrayOf(1, 0, 0, -1, 0)
private val dc = charArrayOf('D', 'R', '-', 'U', 'L')
private data class Tank(val y: Int, val x: Int, val n: Int)
fun main() = with(System.`in`.bufferedReader()) {
   val n = readLine().toIntOrNull() ?: error("Invalid toInt")

   if(n !in 3..500) error("Invalid N's range")

   map = Array(n) { IntArray(n) }
   val queue: Queue<Tank> = PriorityQueue()

   // 위치 입력 받기
   for (i in 1..n) {
      val (r, c) = StringTokenizer(readLine()).run {
         nextToken().toInt() to nextToken().toInt()
      }

      queue += Tank(r - 1, c - 1, i)
      map[r - 1][c - 1] = i
   }

   bfs(queue)

   printMap(map)
}

private fun bfs(queue: Queue<Tank>) {
   while (queue.isNotEmpty()) {
      val cur = queue.poll()
      println(cur)
   }
}

fun printMap(arr: Array<IntArray>) {
   for (ints in arr) {
      println(ints.joinToString("\t"))
   }
}

fun isUniqueRowCol(y: Int, x: Int, map: Array<IntArray>) {

}