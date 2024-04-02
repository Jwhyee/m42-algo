package COCI06

/**
 * ### 문제 설명
 * - 비밀 요원 207인 유명한 제임스 본드는 모두가 안다.
 * - 한 가지 덜 알려진 사실은 실제로 대부분의 임무를 그의 사촌인 지미 본드가 완료했다는 것이다.
 * - 제임스 본드는 임무를 받을 때마다 지미 본드에게 맡기기 귀찮아서 도움을 청했다.
 * - 매달 제임스 본드는 임무 목록을 받는다.
 * - 모든 정보를 이용해서 특정 미션이 지미 본드에 의해 성공할 수 있는 확률을 계산해야 한다.
 * - 데이터를 처리하면서 모든 미션이 성공적으로 완료될 확률이 가장 높은 배치를 찾아야 한다.
 * ### 제한 사항
 * - 첫 줄에 정수 N이 주어진다.(미션의 수)
 * - N개의 라인을 통해 N개의 0 ~ 100 사이의 수가 주어진다.
 * - 각 수는 지미 본드가 미션을 성공적으로 완수할 확률이다.
 * - 지미 본드가 모든 미션을 성공적으로 완수할 확률의 최댓값을 백분율로 출력해라.
 * */

import kotlin.math.max

private lateinit var jimmy: IntArray
private lateinit var visited: BooleanArray
private lateinit var numbers: Array<DoubleArray>

fun main() = with(System.`in`.bufferedReader()) {

   val n = readLine().toInt()

   // 전역 배열 초기화
   visited = BooleanArray(n) { false }
   jimmy = IntArray(n)
   // 각 라인에서 입력된 수(백분율)을 소수로 변환
   numbers = Array(n) {
      readLine().split(" ").map { it.toDouble() / 100 }.toDoubleArray()
   }

   // 백트래킹을 통해 최대 확률 찾기
   val max = backTracking()
   println("%.6f".format(max * 100))
}

private fun backTracking(depth: Int = 0): Double {
   // 깊이가 n과 같아질 경우 확률 계산
   if (depth == numbers.size) {
      // 100%를 기준으로 값 구하기
      var cur = 1.0
      // 배열을 돌면서 cur 구하기
      for (i in numbers.indices) {
//         println("cur = $cur \t jimmy[$i] = ${jimmy[i]} \t numbers[$i][${jimmy[i]}] = ${numbers[i][jimmy[i]]}")
         cur *= numbers[i][jimmy[i]]
      }
      return cur
   }

   // 최대를 0%로 두고 시작
   var max = 0.0

   // 백트래킹
   for (i in numbers.indices) {
      // 아직 방문하지 않았을 경우
      if (!visited[i]) {
         // 방문 처리
         visited[i] = true
         // 지미가 처리할 인덱스를 저장
         jimmy[depth] = i
         // 백트래킹을 통해 최대값 갱신
         max = max(max, backTracking(depth + 1))
         // 다음 루프에서도 사용하기 위해 방문 해제
         visited[i] = false
      }
   }
   return max
}