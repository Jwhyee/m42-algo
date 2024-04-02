package COCI06

import java.util.*
import kotlin.math.min

/**
 * 목표 : 화가와 세 마리의 고슴 도치가 최대한 빨리 물에 닿지 않고 비버의 소굴로 들어가야 한다.
 * - 지도의 크기 : R, C / 빈 지역 : '.' / 물에 잠긴 곳 : '*' / 바위 'X' / 비버의 소굴 : 'D' / 화가와 고슴도치 : 'S'
 * ### 요구 사항
 * - 화가와 고슴도치(이하 화가)는 매 분마다 상, 하, 좌, 우로 움직일 수 있다.
 * - 매 분마다 물은 빈 지역으로 범람한다.
 * - 화가는 물과 바위를 통과할 수 없다.
 * - 물은 바위와 비버의 소굴을 통과할 수 없다.
 * - 지도가 주어졌을 때, 화가가 가장 빠르고 안전하게 비버의 소굴에 도착할 수 있는 시간을 구하라
 * - 물 및 화가가 비버의 댐까지 얼마나 빠르게 도착하는지 구한다.
 * ### 제한 사항
 * - '.', '*', 'X' 는 여러 개가 주어질 수 있다.
 * - 'D', 'S' 는 오직 한 개만 주어진다.
 * */

const val PAINTER = 'S' // 83
const val BEAVER = 'D' // 68
const val WATER = '*' // 42
const val FIELD = '.'
private data class Pos(val c: Char, val y: Int, val x: Int, val cnt: Int)
fun main() = with(System.`in`.bufferedReader()) {
   // 첫 줄에 지도의 크기 R, C가 주어진다.
   val (r, c) = readLine().split(" ").map { it.toInt() }

   // 시작 위치를 저장할 지도 배열
   val map = Array(r) { CharArray(c) }

   // 물을 먼저 범람시키고, 화가가 도착할 수 있는지를 확인한다.
   val pq: PriorityQueue<Pos> = PriorityQueue() { a, b -> a.c - b.c }

   // 지도를 입력 받는다.
   for (y in 0..<r) {
      val line = readLine().toCharArray()
      for (x in 0..<c) {
         val cur = line[x]
         map[y][x] = cur
         // 만약 현재 문자가 화가이거나 물일 경우 우선순위 큐에 저장한다.
         if (cur == PAINTER || cur == WATER) {
            pq += Pos(cur, y, x, 0)
         }
      }
   }

   val result = bfs(r, c, pq, map)

   println(if(result == Int.MAX_VALUE) "KAKTUS" else result)

}

private val dx = intArrayOf(0, 1, 0, -1)
private val dy = intArrayOf(1, 0, -1, 0)

private fun bfs(r: Int, c: Int, queue: Queue<Pos>, map: Array<CharArray>): Int {
   // 물이 얼마나 빨리 덮이는지 확인하기 위한 배열
   val water = Array(r) { IntArray(c) }
   // 화가가 얼마나 빨리 도착할 수 있는지 확인하기 위한 배열
   val painter = Array(r) { IntArray(c) }

   var result = Int.MAX_VALUE

   // 큐가 빌 때까지 순회한다.
   while (queue.isNotEmpty()) {
      val cur = queue.poll()
      val nextCnt = cur.cnt + 1

      // 다음 좌표 탐색을 위해 4방향 순회
      for (i in 0..3) {
         val ny = cur.y + dy[i]
         val nx = cur.x + dx[i]

         // 만약 다음 좌표가 범위 내부에 있을 경우 큐에 넣는 작업 진행
         if (ny in 0..<r && nx in 0..<c) {
            when (cur.c) {
               // 현재 순서가 물일 경우
               WATER -> {
                  if (map[ny][nx] == FIELD) {
                     // 다음 좌표가 땅일 경우, 지도에서 다음 좌표를 물로 변경한다.
                     map[ny][nx] = WATER
                     // 물 배열에 (현재까지의 분 + 1)을 저장한다.
                     water[ny][nx] = nextCnt
                     // 계속 범람시키기 위해 큐에 추가
                     queue += Pos(cur.c, ny, nx, nextCnt)
                  } else if (map[ny][nx] == WATER && water[ny][nx] > nextCnt) {
                     // 현재 순서와 다음 좌표가 모두 물이면서,
                     // 다음 좌표까지 걸린 분이 (현재까지의 분 + 1)보다 더 클 경우, 최소값으로 갱신한다.
                     water[ny][nx] = nextCnt
                     queue += Pos(cur.c, ny, nx, nextCnt)
                  }
               }
               // 현재 순서가 화가일 경우
               PAINTER -> {
                  // 다음 좌표에 비버의 동굴에 도착했을 경우 결과의 최소값을 갱신한다.
                  if (map[ny][nx] == BEAVER) {
                     result = min(result, nextCnt)
                  }
                  // 다음 좌표가 땅
                  // 혹은
                  // 다음 좌표가 물이면서 화가가 (현재까지 이동한 시간 + 1)보다 물이 도착한 시간보다 작을 경우
                  // -> 물이 범람하기 전에 움직였다는 의미
                  if (map[ny][nx] == FIELD || (map[ny][nx] == WATER && water[ny][nx] > nextCnt)) {
                     // 다음 좌표를 화가로 변경하고, 화가 배열의 다음 위치에 시간을 갱신한다.
                     map[ny][nx] = PAINTER
                     painter[ny][nx] = nextCnt
                     queue += Pos(cur.c, ny, nx, nextCnt)
                  } else if (map[ny][nx] == PAINTER && nextCnt < painter[ny][nx]) {
                     // 다음 좌표가 화가이면서, (현재까지 이동한 시간 + 1)이 기존 화가의 이동 시간보다 작을 경우
                     // 시간을 갱신한다.
                     painter[ny][nx] = nextCnt
                     queue += Pos(cur.c, ny, nx, nextCnt)
                  }
               }
            }
         }
      }
   }
   return result
}