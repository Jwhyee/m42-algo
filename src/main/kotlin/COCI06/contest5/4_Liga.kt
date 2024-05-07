package COCI06.contest5

import java.util.StringTokenizer

/**
 * ### 문제 설명
 * - 한 팀이 이길 때마다 3점을 획득하고, 비기면 1점을 획득한다.
 * - 첫 줄에는 정수로 팀의 수가 주어진다.
 * - N개의 행에는 한 팀에 대한 5개 데이터가 나온다.(값을 알 수 없을 경우 물음표가 들어온다.)
 * - 팀의 경기 수 / 이긴 경기 수 / 비긴 경기 수 / 진 경기 수 / 얻은 총점
 * - 각 팀의 최대 경기 수는 100회이다.
 *
 * ### 예제 1번
 *
 * - 각 팀의 승무패의 합이 최대 100이다.
 *
 * - 27 21 3 3 66
 * - 27 18 6 3 ?
 * - ? 15 5 7 50
 * - ? 14 7 5 ?
 * - ? 14 ? 8 47
 * -
 * - 27 21 3 3 66
 * - 27 18 6 3 60
 * - 27 15 5 7 50
 * - 26 14 7 5 49
 * - 27 14 5 8 47
 * */
private const val UNKNOWN = -1001
private const val WIN_SCORE = 3
fun main() = with(System.`in`.bufferedReader()) {
   val n = readLine().toIntOrNull() ?: error("Invalid input N")
   if (n !in 1..1000) error("Invalid N range")

   val arr = Array(n) { IntArray(5) }

   repeat(n) { i ->
      val st = StringTokenizer(readLine())
      var j = 0
      while (st.hasMoreTokens()) {
         val next = st.nextToken()
         arr[i][j] = if(next == "?") UNKNOWN else next.toIntOrNull() ?: error("Invalid score")
         j++
      }
      // 100번 이겼을 경우 비김과 진 횟수를 0으로 고정
      if(arr[i][1] == 100) {
         arr[i][2] = 0
         arr[i][3] = 0
      }
      if(arr[i][2] == 100) {
         arr[i][1] = 0
         arr[i][3] = 0
      }
      if(arr[i][3] == 100) {
         arr[i][1] = 0
         arr[i][2] = 0
      }
      if(arr[i][1] + arr[i][2] == 100) arr[i][3] = 0
      if(arr[i][2] + arr[i][3] == 100) arr[i][1] = 0
      if(arr[i][1] + arr[i][3] == 100) arr[i][2] = 0
      if(arr[i][0] == 0) repeat(5) { arr[i][it] = 0 }
   }

   repeat(n) { i ->
      val cur = arr[i]
      while (true) {
         if (cur[0] != UNKNOWN && cur[1] != UNKNOWN && cur[2] != UNKNOWN && cur[3] != UNKNOWN && cur[4] != UNKNOWN) break
         if (cur[0] == UNKNOWN) {
            if(cur[1] != UNKNOWN && cur[2] != UNKNOWN && cur[3] != UNKNOWN)
               cur[0] = cur[1] + cur[2] + cur[3]
         }
         if (cur[1] == UNKNOWN) {
            if(cur[2] != UNKNOWN && cur[4] != UNKNOWN)
               cur[1] = (cur[4] - cur[2]) / WIN_SCORE
         }
         if (cur[2] == UNKNOWN) {
            if(cur[1] != UNKNOWN && cur[4] != UNKNOWN)
               cur[2] = cur[4] - (cur[1] * 3)
         }
         if (cur[3] == UNKNOWN) {
            if(cur[0] != UNKNOWN && cur[1] != UNKNOWN && cur[2] != UNKNOWN)
               cur[3] = cur[0] - (cur[1] + cur[2])
         }
         if (cur[4] == UNKNOWN) {
            if(cur[1] != UNKNOWN && cur[2] != UNKNOWN)
               cur[4] = (cur[1] * WIN_SCORE) + cur[2]
         }
      }

   }

   for (ints in arr) {
      println(ints.joinToString(" "))
   }
}