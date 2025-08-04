# Section 2. Object클래스
* 부모가 없으면 묵시적으로 Object 클래스를 상속받음
* 객체 정보 출력
```java
Child child = new Child();
String string = child.toString(); //객체의 정보를 String으로 변환
System.out.println(string); //변환된 객체의 정보를 출력
```
* 모든 클래스가 Object클래스를 상속받는 이유
    1. 공통 기능을 제공하기 위해
    2. 다형성의 기본을 구현하기 위해
* Object가 제공하는 기능
  * toString() : 객체의 정보를 제공
  * equals() : 객체의 같음을 비교
  * getClass() : 객체의 클래스 정보 제공
* Object는 모든 객체를 참조할 수 있음 -> 보관하고 싶은 객체가 모두 타입이 다르다면 Object에 보관하면 된다
* Object는 다른 클래스의 메서드를 모르기 때문에 아래와 같이 다운캐스팅을 해야한다
```java
private static void action(Object obj){
        //obj.sound(); //컴파일 오류, Object는 sound()가 없다
        //obj.move(); //컴파일 오류, Object는 move()가 없다

        //객체에 맞는 다운캐스팅 필요
        if(obj instanceof Dog dog){
            dog.sound();
        } else if(obj instanceof Car car){
            car.move();
        }
    }
```
* Object를 print하면 자동적으로 .toString() 메서드가 실행된다
```java
        Object object = new Object();
        String string = object.toString();

        //toString() 반환값 출력
        System.out.println(string);

        //object 직접 출력
        System.out.println(object);
```
* println() 메서드는 Object 매개변수를 사용하고 toString() 메서드를 호출하기 때문에, 모든 객체의 정보를 출력할 수 있었던 것이다
* 정적 의존관계 : 코드에서 클래스 내에서 사용하는 타입들만 보고 파악할 수 있는 의존관계를 말한다
* 동적 의존관계 : 프로그램을 실행 중에 어떤 인스턴스를 사용하는지 알 수 있는 의존관계를 말한다
* 동일성: 참조하는 객체가 동일한지 확인
* 동등성: equals()와 같이 논리적으로 두 객체가 동등한지 확인
* Object와 같은 추상적인 객체의 equals()는 클래스마다 동등성이라는 개념이 다르다, 아래와 같이 최소 하나의 명확한 기준을 잡고 비교를 해야한다
```java
@Override
    public boolean equals(Object obj) {
        UserV2 user = (UserV2) obj; //매개변수가 Object이기 때문에 인스턴스 변수를 사용하기 위해 다운캐스팅
        return id.equals(user.id);
    }
```
* 정확한 equals()를 구현하려면 기존 equals() 오버라이딩을 제거하고, commend + N 으로 equals()를 만든다
* equals() 메서드를 구현할 때 반사성(Reflexivity), 대칭성(Symmetry), 추이성(Transiticity), 일관성(Consistency), null에 대한 비교의 규칙을 만족해야한다

# Section 3. 불변 객체
* 사이드 이펙트(Side Effect): 프로그래밍에서 어떤 계산이 주된 작업 외에 추가적인 부수 효과를 일으키는 것
* 자바에서 여러 참조형 변수가 하나의 객체를 참조하는 공유 참조 문제는 피할 수 없다
* 아래 코드는 b 객체만  "부산"으로 변경된다고 추측할 수 있다
```java
        change(b, "부산");
        System.out.println("부산 -> b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
```
하지만 a와 b는 같은 객체를 참조하기 때문에 a와 b 모두 "부산"으로 변경되는 문제가 발생한다   
-> 해결책, 객체의 값을 변경하지 못하게 불변 객체(Immutable Object)로 만들면 된다 (객체의 변수를 final로 선언, 값을 변경할 수 있는 setValue() 메서드 제거, 생성자만을 통해 값 설정 등)
* 아래와 같이 불변 객체를 다루는 메서드에서는 새로운 객체를 할당하는 경우가 있다, 이럴 때는 분명히 해당메서드의 반환값을 받아주여야 한다, 그렇지 않으면 반환 객체가 사라진다
```java
    public ImmutableObj add(int addValue) {
        int result = value + addValue;
        return new ImmutableObj(result);
    }
```
* 클래스에는 기본적으로 toString() 메서드가 존재하므로 따로 호출하지 않아도 된다, 오버라이딩 했을떄도 마찬가지다
* 관례적으로 withXxx() 같은 이름의 메서드는 불변 객체의 메서드이다. 이떄는 지정된 수정사항을 포함되는 객체의 새 인스턴스를 반환한다는 의미를 가진다

# Section 4. String 클래스
* String은 클래스임, int, boolean은 참조형
* 자바에서 char -> 2byte, 단순히 영어, 숫자 -> 1byte
* String 클래스의 주요 메서드
  * length()` : 문자열의 길이를 반환한다.
  * `charAt(int index)` : 특정 인덱스의 문자를 반환한다.
  * `substring(int beginIndex, int endIndex)` : 문자열의 부분 문자열을 반환한다.
  * `indexOf(String str)` : 특정 문자열이 시작되는 인덱스를 반환한다.
  * `toLowerCase()` , `toUpperCase()` : 문자열을 소문자 또는 대문자로 변환한다.
  * `trim()` : 문자열 양 끝의 공백을 제거한다.
  * `concat(String str)` : 문자열을 더한다.
* 풀(Pool): 공용 자원을 모아둔 곳
* 문자열 비교는 항상 '==' 대신 equals()를 사용해야한다. 여러 개발자가 작업하는 과정에서 혼란을 야기할 수 있음
* String은 불변 객체이므로 반환 객체를 새로 받아줘야함
* 불변 객체로 구현 된 이유: 문자열 풀에서 같은 문자를 참조하는 참조 객체들의 값이 의도치 않게 변경되는 사이드 이펙트 문제가 발생할 수 있기 때문이다
* CharSequence: String과 StringBuilder의 상위 타입임. 문자열을 처리하는 다양한 객체를 받을 수 있음
* 공백 제거 메서드 trim()과 strip()의 차이
  * trim(): 문자열 양쪽 끝의 공백을 제거한다. 단순 화이트 스페이스만 제거 가능
  * strip(): 화이트 스페이스와 유니코드 공백을 포함하여 제거한다(자바 11부터 지원)
* 주요 메서드 사용법
```java
// 문자열 기본 정보
String str = "Hello, Java!";
System.out.println("문자열의 길이: " + str.length());
        System.out.println("문자열이 비어 있는지: " + str.isEmpty());
        System.out.println("문자열이 비어 있거나 공백인지: " + str.isBlank());
        System.out.println("공백 문자열이 비어 있거나 공백인지: " + "           ".isBlank());

// 문자 접근
char c = str.charAt(7);
System.out.println("7번 인덱스의 문자 = " + c);

// 문자열 비교
String str1 = "Hello, Java!";
String str2 = "hello, java!";
String str3 = "Hello, World!";

System.out.println("str1 equals str2: " + str1.equals(str2));
        System.out.println("str1 equalsIgnoreCase str2: " + str1.equalsIgnoreCase(str2));
        System.out.println("'b' compareTo 'a': " + "b".compareTo("a"));
        System.out.println("str1 compareTo str3: " + str1.compareTo(str3));
        System.out.println("str1 compareToIgnoreCase str2: " + str1.compareToIgnoreCase(str2));
        System.out.println("str1 starts with 'Hello': " + str1.startsWith("Hello"));
        System.out.println("str1 ends with 'Java!': " + str1.endsWith("Java!"));

// 문자열 포함 여부 및 위치
str = "Hello, Java! Welcome to Java world";
        System.out.println("문자열에 'Java'가 포함되어 있는지: " + str.contains("Java"));
        System.out.println("'Java'의 첫 번째 인덱스: " + str.indexOf("Java"));
        System.out.println("인덱스 10부터 'Java'의 인덱스: " + str.indexOf("Java", 10));
        System.out.println("'Java'의 마지막 인덱스: " + str.lastIndexOf("Java"));

// 부분 문자열, 연결, 치환
str = "Hello, Java! Welcome to Java";
        System.out.println("인덱스 7부터의 부분 문자열: " + str.substring(7));
        System.out.println("인덱스 7부터 12까지의 부분 문자열: " + str.substring(7, 12));
        System.out.println("문자열 결합: " + str.concat("!!!"));
        System.out.println("'Java'를 'World'로 대체: " + str.replace("Java", "World"));
        System.out.println("첫 번째 'Java'를 'World'로 대체: " + str.replaceFirst("Java", "World"));

// 대소문자 변환, 공백 제거
String strWithSpaces = "     Java Programming ";
System.out.println("소문자로 변환: " + strWithSpaces.toLowerCase());
        System.out.println("대문자로 변환: " + strWithSpaces.toUpperCase());
        System.out.println("공백 제거(trim): '" + strWithSpaces.trim() + "'");
        System.out.println("공백 제거(strip): '" + strWithSpaces.strip() + "'");
        System.out.println("앞 공백 제거(stripLeading): '" + strWithSpaces.stripLeading() + "'");
        System.out.println("뒤 공백 제거(stripTrailing): '" + strWithSpaces.stripTrailing() + "'");

// split & join
str = "Apple,Banana,Orange";
String[] splitStr = str.split(",");
for (String s : splitStr) {
        System.out.println(s);
}

String joinedStr = String.join("-", "A", "B", "C");
System.out.println("연결된 문자열 = " + joinedStr);

String result = String.join("-", splitStr);
System.out.println("문자열 배열 연결 result = " + result);

// valueOf & toCharArray
int num = 100;
boolean bool = true;
Object obj = new Object();
str = "Hello, Java!";

String numString = String.valueOf(num);
System.out.println("숫자의 문자열 값: " + numString);

String boolString = String.valueOf(bool);
System.out.println("불리언의 문자열 값: " + boolString);

String objString = String.valueOf(obj);
System.out.println("객체의 문자열 값: " + objString);

// 빈 문자열 + 숫자
String numString2 = "" + num;
System.out.println("빈 문자열 + num: " + numString2);

// 문자열을 문자 배열로 변환
char[] strCharArray = str.toCharArray();
System.out.println("문자열을 문자 배열로 변환: " + strCharArray);
for (char ch : strCharArray) {
        System.out.print(ch);
}
        System.out.println();

// format, printf
String format1 = String.format("num: %d, bool: %b, str: %s", num, bool, str);
System.out.println(format1);

String format2 = String.format("숫자: %.2f", 10.1234);
System.out.println(format2);

System.out.printf("숫자: %.3f\n", 10.1234);

// 정규 표현식 matches
String regex = "Hello, (Java!|World)";
System.out.println("'str'이 패턴과 일치하는가? " + str.matches(regex));

```

* StringBuilder는 String과 다르게 private이 없어 수정이 가능하다(가변적이다)
  * 장점: 메모리 사용 감소, 성능 향상
  * 단점: 사이드 이펙트 주의
  * 보편적인 사용법 추천: 먼저 StringBuilder로 문자열을 만들고 toString으로 변환
* 보통 String은 아래와 같이 최적화 한다
```java
String result = str1 + str2;
//아래와 같이 최적화
String result = new StringBuilder().append(str1).append(str2).toString();
```
* String으로 문자열을 10만번 이어붙이면 2.5초가 걸린다. 매 반복마다 새로운 String객체를 생성하기 때문이다
  * StringBuilder으로 하면 0.003초가 걸린다. 기존의 객체를 수정할 수 있기 때문이다
* StringBuilder를 직접 사용하면 더 좋은 경우
  * 반복문에서 반복해서 문자를 연결할 때
  * 조건문을 통해 동적으로 문자열을 조합할 때
  * 복잡한 문자열의 특정 부분을 변경해야 할 때
  * 매우 긴 대용량 문자열을 다룰 떄
* StringBuffer
  * StringBuilder과 같은 기능을 한다
  * 내부에 동기화가 되어, 멀티 쓰레드 환경에서 안전하다
  * 하지만 동기화 오버헤드 때문에 속도가 비교적 느리다
* 메서드 체이닝: 자기 자신의 참조값을 반환하여 메서드 호출을 여러번 연결해서 사용하는 것
```java
        int result = adder.add(1).add(2).add(3).getValue();
```
* StringBuilder는 메서드 체이닝 기법을 지원한다
* indexOf()의 첫 번째 인자는 대상 단어(String | char)이고 두 번째 인자는 탐색 시작 지점이다. 세 번째 인자는 탐색 종료 지점이다. 찾지 못한다면 -1을 반환한다



