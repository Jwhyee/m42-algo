package COCI06

/**
 * ### 문제 설명
 * - 피터팬 프레임은 각 문자마다 다이아몬드 문양으로 장식하는 것이다.
 * - 그러나 모든 프레임이 같으면 재미없기 때문에 매 세 번째 문자마다 웬디 프레임을 적용한다.
 * ### 요구사항
 * - 입력은 오직 첫 줄에만 최대 15자의 대문자만 들어온다.
 * > [링크](https://dmoj.ca/problem/coci06c1p3)
 * */
private var curPosition = 0
private lateinit var result: Array<CharArray>
fun main() = with(System.`in`.bufferedReader()) {
   val text = readLine()

   // 만약 한 글자가 주어질 경우 결과 값의 가로 길이는 5자이다.
   // 그렇지 않을 경우 (글자 길이 * 4) + 1을 한 값이 가로 길이가 된다.
   val widthLen = if(text.length == 1) 5 else (text.length * 4) + 1

   // 격자를 만들기 위한 2차원 배열 생성
   result = Array(5) { CharArray(widthLen) {'.'} }

   // 각 문자를 돌면서 문양을 만든다.
   for (i in text.indices) {
      // 매 3번째 자리일 경우 웬디 프레임을 적용한다.
      val c = (if((i + 1) % 3 == 0) FrameType.WENDY else FrameType.PETER_PAN).c

      // 현재 포지션을 -1 시킴 ->
      curPosition--
      makeFrame("..$c..")
      makeFrame(".$c.$c.")
      makeFrame("$c.${text[i]}.$c")
      makeFrame(".$c.$c.")
      makeFrame("..$c..")
   }

   for (i in 0..4) {
      println(result[i].joinToString(""))
   }

}

private enum class FrameType(val c: Char) {
   PETER_PAN('#'),
   WENDY('*');
}
private fun makeFrame(str: String) {
   curPosition++
   repeat(5) { idx ->
      // 만약 현재 문자열이 #인데, 이미 격자에 *이 찍혀있을 경우에는 그러지 않고 넘어간다.
      // 이 외의 경우에는 그려준다.
      if (str[idx] != '#' || result[idx][curPosition] != '*') {
         result[idx][curPosition] = str[idx]
      }
   }
}