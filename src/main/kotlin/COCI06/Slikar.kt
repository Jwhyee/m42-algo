package COCI06

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.math.max

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
 * ### 제한 사항
 * - '.', '*', 'X' 는 여러 개가 주어질 수 있다.
 * - 'D', 'S' 는 오직 한 개만 주어진다.
 * > 첫 풀이 : 26분 + 40/100(1 WA, 5 TLE)
 * */
fun main() = with(System.`in`.bufferedReader()) {
   val (r, c) = StringTokenizer(readLine()).run {
      nextToken().toInt() to nextToken().toInt()
   }

   val map = Array(r) { CharArray(c) }

   val sQueue: Queue<Node> = LinkedList()
   val wQueue: Queue<Node> = LinkedList()

   var sCnt = 0
   var dCnt = 0
   repeat(r) { y ->
      val arr = readLine().toCharArray()
      repeat(c) { x ->
         val cur = arr[x]
         when (cur) {
            'S' -> {
               sCnt++
               sQueue += Node(y, x, cur, 0)
            }
            'D' -> dCnt++
            '*' -> wQueue += Node(y, x, cur, 0)
         }
         map[y][x] = cur
      }
   }

   if(sCnt != 1 || dCnt != 1) throw IllegalArgumentException("S와 D는 각각 1개 이상일 수 없습니다.")

   val painterResult = bfs(map, sQueue, r, c)
   val floodResult = bfs(map, wQueue, r, c)

   if (painterResult >= floodResult) println("KAKTUS")
   else println(painterResult)

}

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

private fun bfs(map: Array<CharArray>, queue: Queue<Node>, r: Int, c: Int): Int {
   val visited = Array(r) { BooleanArray(c) }

   var max = -1

   while (queue.isNotEmpty()) {
      val cur = queue.poll()

      visited[cur.y][cur.x] = true

      for (i in 0 until 4) {
         val ny = cur.y + dy[i]
         val nx = cur.x + dx[i]
         if (ny in 0 until r && nx in 0 until c && !visited[ny][nx]) {
            when (cur.c) {
               '*' -> {
                  if(map[ny][nx] == 'D') max = max(cur.cnt + 1, max)
                  if(map[ny][nx] != 'X' && map[ny][nx] != '*' && map[ny][nx] != 'D')
                     queue += Node(ny, nx, cur.c, cur.cnt + 1)
               }
               'S' -> {
                  if(map[ny][nx] == 'D') return cur.cnt + 1
                  if(map[ny][nx] != 'X' && map[ny][nx] != '*')
                     queue += Node(ny, nx, cur.c, cur.cnt + 1)
               }
            }
         }
      }

   }

   return max
}