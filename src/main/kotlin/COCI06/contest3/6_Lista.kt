package COCI06.contest3

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

/**
 * ### 문제 설명
 * - 미르코는 생일 선물로 새로운 이중 링크 리스트를 받았다.
 * - 리스트는 1번부터 N개까지의 노드들로 이루어져있다.
 * - 미르코는 이 장난감을 갖고 놀면서 초기 상태로 재구성할 수 있도록 각 움직임을 종이에 적었다.
 * - 하지만 노드 X가 이동 전에 어디있었는지는 알 수 없었고, 현재 위치만 알고 있다.
 * - 미르코가 작성한 로그를 보고, 목록의 초기 상태를 최소한의 움직임으로 찾아라.
 * ### 제한 사항
 * - 첫 줄에는 2개의 정수 N, M이 주어진다.(2 <= N <= 500,000 / 0 <= M <= 100,000)
 * - 다음 M개의 줄에는 미르코가 움직인 A 혹은 B에 대한 정보와 X, Y에 대한 정보가 주어진다.
 * - A -> 노드 X를 Y앞으로 옮긴다.
 * - B -> 노드 X를 Y뒤로 옮긴다.
 * */
private lateinit var ordered: Array<Boolean>

fun main() = with(System.`in`.bufferedReader()) {
   val (n, m) = StringTokenizer(readLine()).run {
      (nextToken().toIntOrNull() ?: 0) to (nextToken().toIntOrNull() ?: -1)
   }
   // 밸리데이션
   if(n == 0 || n !in 2..500_000 || m == -1 || m !in 0..100_000) error("Invalid N or M")

   val queue: Queue<Int> = LinkedList()
   repeat(n) {
      queue += (it + 1)
   }

   println()
   println(queue)
   ordered = Array(n + 1) { false }

   // 미르코가 움직인 로그대로 순서 변경
   repeat(m) {
      val (c, x, y) = StringTokenizer(readLine()).run {
         Triple(nextToken()[0], nextToken().toInt(), nextToken().toInt())
      }
      when (c) {
         'A' -> {
            insertAtInFrontOfY(queue, x, y)
            println(queue)
         }
         'B' -> {
            insertAtAfterY(queue, x, y)
            println(queue)
         }
      }
   }

   restoreList(queue)
}

private fun restoreList(queue: Queue<Int>) {
   val sb = StringBuilder()
   val size = queue.size - 1
   var arr = queue.toTypedArray()
   for (i in 0..<size) {
      val cur = arr[i]
      val next = i + 1
      println("cur = $cur / next = $next")
      if (cur == next) {
         println("pass")
         continue
      }

      // 현재 자리에 next가 와야한다.

      /** [6, 1, 3, 5, 2, 4] 기준으로 봤을 때,
       * [A 1 6] / [B 6 1]이 베스트인지,
       *
       * */
      if (cur > next) {
         println("A $next $cur")
         sb.append("A $next $cur")
         insertAtInFrontOfY(queue, next, cur)
         println(queue)
      } else {
         println("B $cur $next")
         sb.append("B $cur $next")
         insertAtAfterY(queue, cur, next)
         println(queue)
      }
      arr = queue.toTypedArray()
   }
}

private fun isOrdered(queue: Queue<Int>): Boolean {
   var temp = 0
   for (number in queue) {
      if (number == temp + 1) {
         temp = number
         ordered[number] = true
      }
      else return false
   }
   return true
}

/** 노드 X를 Y 앞으로 옮긴다. */
private fun insertAtInFrontOfY(queue: Queue<Int>, x: Int, y: Int) {
   val size = queue.size
   for (i in 0..<size) {
      val cur = queue.poll()
      when (cur) {
         x -> {}
         y -> {
            queue += x
            queue += y
         }
         else -> queue += cur
      }
   }
}

/** 노드 X를 Y 뒤로 옮긴다 */
private fun insertAtAfterY(queue: Queue<Int>, x: Int, y: Int) {
   val size = queue.size
   for (i in 0..<size) {
      val cur = queue.poll()
      when (cur) {
         x -> {}
         y -> {
            queue += y
            queue += x
         }
         else -> queue += cur
      }
   }
}