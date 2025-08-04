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
* 