package COCI06

import java.util.*

private data class Node(val y: Int, val x: Int, val c: Char, var cnt: Int)

/**
 * 목표 : 화가와 세 마리의 고슴 도치가 최대한 빨리 물에 닿지 않고 비버의 소굴로 들어가야 한다.
 * - 지도의 크기 : R, C / 빈 지역 : '.' / 물에 잠긴 곳 : '*' / 바위 'X' / 비버의 소굴 : 'D' / 화가와 고슴도치 : 'S'
 * ### 요구 사항
 * - 화가와 고슴도치(이하 화가)는 매 분마다 상, 하, 좌, 우로 움직일 수 있다.
 * - 매 분마다 물은 빈 지역으로 범람한다.
 * - 화가는 물과 바위를 통과할 수 없다.
 * - 물은 바위와 비버의 소굴을 통과할 수 없다.
 * - 지도가 주어졌을 때, 화가가 가장 빠르고 안전하게 비버의 소굴에 도착할 수 있는 시간을 구하라
 * > 첫 풀이 : 26분 + 40/100(1 WA, 5 TLE)
 * */
fun main() = with(System.`in`.bufferedReader()) {
   val (r, c) = StringTokenizer(readLine()).run {
      nextToken().toInt() to nextToken().toInt()
   }

   val map = Array(r) { CharArray(c) }

   val queue: Queue<Node> = LinkedList()

   repeat(r) { y ->
      val arr = readLine().toCharArray()
      repeat(c) { x ->
         val cur = arr[x]
         if (cur == 'S' || cur == '*') queue += Node(y, x, cur, 0)
         map[y][x] = cur
      }
   }

   val result = bfs(map, queue, r, c)
   if (result == -1) println("KAKTUS")
   else println(result)

}

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

private fun bfs(map: Array<CharArray>, queue: Queue<Node>, r: Int, c: Int): Int {
   val pVisited = Array(r) { BooleanArray(c) }
   val wVisited = Array(r) { BooleanArray(c) }

   val list = queue.sortedByDescending { it.c }
   queue.clear()
   queue += list

   while (queue.isNotEmpty()) {
      val cur = queue.poll()
      val y = cur.y
      val x = cur.x
      val ch = cur.c

      if (ch == 'S') {
         pVisited[y][x] = true
      } else {
         wVisited[y][x] = true
      }

      for (i in 0 until 4) {
         val ny = y + dy[i]
         val nx = x + dx[i]

         if (ny in 0 until r && nx in 0 until c) {
            when (ch) {
               'S' -> {
                  if (!pVisited[ny][nx] && map[ny][nx] != 'X' && map[ny][nx] != '*') {
                     if (map[ny][nx] == 'D') {
                        return cur.cnt + 1
                     }
                     map[y][x] = '.'
                     map[ny][nx] = ch
                     queue += Node(ny, nx, ch, cur.cnt + 1)
                  }
               }
               '*' -> {
                  if (!wVisited[ny][nx] && map[ny][nx] != 'X' && map[ny][nx] != 'D') {
                     map[ny][nx] = '*'
                     queue += Node(ny, nx, ch, 0)
                  }
               }
            }
         }
      }
   }
   return -1
}