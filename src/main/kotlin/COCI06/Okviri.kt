package COCI06

var curPosition = 0
lateinit var result: Array<CharArray>
fun main() = with(System.`in`.bufferedReader()) {
   val text = readLine()
   val widthLen = if(text.length == 1) 5 else (text.length * 4) + 1
   result = Array(5) { CharArray(widthLen) }

   for (i in text.indices) {
      val c = (if((i + 1) % 3 == 0) FrameType.WENDY else FrameType.PETER_PEN).c
      curPosition--
      makeFrame("..$c..")
      makeFrame(".$c.$c.")
      makeFrame("$c...$c")
      result[2][curPosition] = text[i]
      makeFrame(".$c.$c.")
      makeFrame("..$c..")
   }

   for (i in 0..4) {
      println(result[i].joinToString(""))
   }

}
private enum class FrameType(val c: Char) {
   PETER_PEN('#'),
   WENDY('*');
}
fun makeFrame(str: String) = buildString {
   curPosition++
   repeat(5) { idx ->
      if (str[idx] != '#' || result[idx][curPosition] != '*') {
         result[idx][curPosition] = str[idx]
      }
   }
}