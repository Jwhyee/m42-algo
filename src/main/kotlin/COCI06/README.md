# 1주차 스터디

- `validation` 신경쓰기

## 3번 Okviri

다음과 같은 패턴이 나온다.

```
..#.    ..#.    ..*..   .#.
.#.#    .#.#    .*.*.   #.#
#.D.    #.O.    *.G.*   .X.
.#.#    .#.#    .*.*.   #.#
..#.    ..#.    ..*..   .#.
```

문자열 길이에 따라 패턴을 만들고 `replace` 처리

## 4번 Slikar

목적에 맞는 최적의 방식을 사용하기

- BFS가 아닌 charArray로도 충분히 풀이가 가능하다.
- 각 물 요소를 찾아서 상하좌우 이동 가능한 만큼 *을 전개한다.

```kotlin
// 의사 코드

while() {
   start = 0
   while (true) {
      val idx = charArrayClone.indexOf('*', start)
      if(idx == -1) break
      start = idx
      charArray[idx + col] = '*'
      charArray[idx - col] = '*'
      charArray[idx + 1] = '*'
      charArray[idx - 1] = '*'
   }
   start = 0
   while (true) {
      val idx = charArrayClone.indexOf('S', start)
      if(idx == -1) break
      start = idx
      charArray[idx + col] = 'S'
      charArray[idx - col] = 'S'
      charArray[idx + 1] = 'S'
      charArray[idx - 1] = 'S'
   }
}

```