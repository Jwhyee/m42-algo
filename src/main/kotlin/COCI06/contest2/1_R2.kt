package COCI06.contest2

/**
 * ### 문제 설명
 * 미르코가 슬라브코에게 R1, R2라는 두 개의 정수를 선물로 줬다.
 * 슬라브코는 즉시 계산하려고했지만, R2를 잃어버렸다. R2를 복원하는 것을 돕자.
 * - [문제 링크](https://dmoj.ca/problem/coci06c2p1)
 * ### 풀이
 * - s = (r1 + r2) / 2
 * - 2 * s = r1 + r2
 * - r2 = 2 * s - r1
 * */
fun main() = with(System.`in`.bufferedReader()) {
   val rList = readLine().split(" ").map { it.toInt() }
   if(rList.size != 2) error("입력이 잘못되었습니다.")

   val r1 = rList[0]
   val s = rList[1]

   println((2 * s) - r1)
}