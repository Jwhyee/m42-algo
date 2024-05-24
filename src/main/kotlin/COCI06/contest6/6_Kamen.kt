package COCI06.contest6

import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

/**
 * ### 문제 해설
 * - 바위 밑에 있는 칸이 벽이거나, 가장 아래 줄이라면, 그 자리에 남는다.
 * - 바위 밑에 있는 칸이 비어있다면, 밑으로 이동한다.
 * - 바위 밑에 있는 칸에 또 다른 바위가 있을 경우
 * - 1. 바위 왼쪽과 왼쪽 아래 칸이 비어있으면, 왼쪽으로 미끄러진다.
 * - 2. 왼쪽으로 못간다면, 오른쪽과 오른쪽 아래 칸이 비어있을 경우 오른쪽으로 미끄러진다.
 * - 3. 위 조건을 모두 만족하지 못하면, 그 자리에 남는다.
 * ### 예제 2번
 * -              1   ->    4   ->    4   ->    6   ->    4   ->   4
 * - ...... -> ...... -> ...... -> ...... -> ...... -> ...... -> ......
 * - ...... -> ...... -> ...O.. -> ...O.. -> ...O.. -> ...O.. -> ...O..
 * - ...XX. -> ...XX. -> ...XX. -> ...XX. -> ...XX. -> ...XX. -> ...XX.
 * - ...... -> ...... -> ...... -> ...... -> ...... -> ...... -> ......
 * - ...... -> ...... -> ...... -> ..O... -> ..O... -> ..O... -> .OO...
 * - .XX... -> .XX... -> .XX... -> .XX... -> .XX... -> .XX... -> .XX...
 * - ...... -> O..... -> O..... -> O..... -> O....O -> O..O.O -> O..O.O
 *
 * */
private data class Rock(val r: Int, val c: Int)
fun main() = with(System.`in`.bufferedReader()) {
   val (r, c) = StringTokenizer(readLine()).run {
      nextToken().toInt() to nextToken().toInt()
   }
   val map = Array(r) { CharArray(c) }

   repeat(r) {
      map[it] = readLine().toCharArray()
   }

   val n = readLine().toIntOrNull() ?: error("Invalid N")
   val order = Array(n) {0}
   repeat(n) {
      order[it] = readLine().toIntOrNull() ?: error("Invalid case")
   }

   val queue: Queue<Rock> = LinkedList()

   repeat(n) {
      queue += Rock(0, order[it] - 1)
      while (queue.isNotEmpty()) {
         val cur = queue.poll()
         if (cur.r + 1 == r - 1 && map[cur.r + 1][cur.c] == '.') {
            map[cur.r + 1][cur.c] = 'O'
         } else if (cur.r + 1 < r) {
            when (map[cur.r + 1][cur.c]) {
               '.' -> { queue += Rock(cur.r + 1, cur.c) }
               'X' -> { map[cur.r][cur.c] = 'O' }
               'O' -> {
                  if (cur.c - 1 >= 0 && map[cur.r][cur.c - 1] == '.' && map[cur.r + 1][cur.c - 1] == '.') {
                     queue += Rock(cur.r, cur.c - 1)
                  } else if (cur.c + 1 < c && map[cur.r][cur.c + 1] == '.' && map[cur.r + 1][cur.c + 1] == '.') {
                     queue += Rock(cur.r, cur.c + 1)
                  } else {
                     map[cur.r][cur.c] = 'O'
                  }
               }
            }
         }
      }
   }
   for (chars in map) {
      println(chars.joinToString(""))
   }
}