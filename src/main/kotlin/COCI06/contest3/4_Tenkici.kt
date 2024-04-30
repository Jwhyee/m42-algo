package COCI06.contest3

import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.abs

/**
 * ### 문제 설명
 * - 미르코는 할아버지의 다락방에서 N개의 2차 세계 대전 탱크 장난감을 발견했다.
 * - 그는 즉시 그의 친구 슬라브코를 불러서 놀았다.
 * - 밥을 먹고 다시 시작하기위해 각 탱크가 각기 다른 행과 열에 위치하도록 재배치하려 한다.
 * - 최소한의 이동 횟수를 사용해 각 행과 각 열에 하나의 탱크가 위치할 수 있는 프로그램을 작성해라.
 *
 * ### 제한 사항
 * - 나무 보드는 N x N 크기이다.
 * - 각 탱크는 이웃된 4칸으로 한 칸씩 전진할 수 있다.
 * - 같은 행이나 같은 열에 있는 탱크를 쏠 수 있다.
 * - 한 칸에 두 개의 탱크가 함께 존재할 수 없다.
 * - 출력의 순서는 유니크하지 않다.
 * - [링크](https://dmoj.ca/problem/coci06c3p4)
 * */
private val dx = intArrayOf(0, 1, 0, 0, -1)
private val dy = intArrayOf(1, 0, 0, -1, 0)
private val dc = charArrayOf('D', 'R', '-', 'U', 'L')
private data class Tank(val y: Int, val x: Int, val n: Int, var isComplete: Boolean = false)
fun main() = with(System.`in`.bufferedReader()) {
   val n = readLine().toIntOrNull() ?: error("Invalid toInt")

   if(n !in 3..500) error("Invalid N's range")

   val map = Array(n) { IntArray(n) }
   val list = mutableListOf<Tank>()

   // 위치 입력 받기
   for (i in 1..n) {
      val (r, c) = StringTokenizer(readLine()).run {
         nextToken().toInt() to nextToken().toInt()
      }
      list += Tank(r - 1, c - 1, i)
      map[r - 1][c - 1] = i
   }

   find(map, list)

   printMap(map)
}

private fun find(map: Array<IntArray>, list: MutableList<Tank>) {
   val existRows = BooleanArray(map.size)
   val existCols = BooleanArray(map.size)
   for (y in map.indices) {
      if(existRows[y]) continue
      for (x in map[0].indices) {
         for (tank in list) {
            if (!tank.isComplete) {

            }
         }
         if(existCols[x]) continue

         val min = findMinMoveTank(y, x, list)
         val curTank = min.first
         val diffY = min.second
         val diffX = min.third
         if(diffY + diffX == 0) {
            curTank.isComplete = true
            break
         } else {

         }
         existRows[y] = true
         existCols[x] = true
      }
   }
}

private fun findMinMoveTank(y: Int, x: Int, list: MutableList<Tank>): Triple<Tank, Int, Int> {
   var min = list[0]
   var minX = Int.MAX_VALUE / 3
   var minY = Int.MAX_VALUE / 3
   println("y = $y / x = $x")
   for (tank in list) {
      val diffY = abs(tank.y - y)
      val diffX = abs(tank.x - x)
      if(minY + minX > diffY + diffX) {
         minY = diffY
         minX = diffX
         min = tank
      }
   }
   return Triple(min, minY, minX)
}

fun printMap(arr: Array<IntArray>) {
   for (ints in arr) {
      println(ints.joinToString("\t"))
   }
}