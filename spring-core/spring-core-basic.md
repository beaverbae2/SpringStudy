# 스프링 기본 원리 - 기본편



## 좋은 객체 지향 프로그래밍이란

### 다형성

> 역할과 구현의 구분, **유연하고 변경에 용이한 코드 구현**

- 비유
  - 운전자 - 자동차
    - 운전자는 자동차의 종류가 바뀌어도 운전을 할 수 있어야함
    - 운전자는 자동차의 역할만 알고 있으면 됌
    - 자동차의 종류를 무한히 확장할 수 있음
    - **운전자에게 영향을 주지 않고**, 자동차의 종류를 바꿀 수 있음
- 역할과 구현의 분리
  - 클라이언트는 대상의 역할(인터페이스)만 인지하면 됌
  - 클라이언트는 구현 대상의 내부 구조 몰라도 괜찮음
  - 클라어언트는 구현 대상의 내부 구조 변경되도 영향 없음
  - 클라이언트는 구현 대상을 변경해도 영향 없음
- 자바
  - 역할 : 인터페이스, 구현 : 인터페이스를 구현한 클래스
  - 설계 시 인터페이스를 먼저 작성 -> 안정적인 설계 필요
  - 오버라이딩
    - 다형성의 구현
    - 구현 객체의 메소드가 실행
    - 런타임 시, 구현 객체 선택 (**유연하게 변경**)
- 본질
  - 객체 지향 : 객체 사이의 **협력 관계** (클라이언트 - 서버)
  - **클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경**
  - 런타임 시, 인터페이스를 구현 객체로 유연하게 변경

<br><br>

## SOLID

요약 : 다형성만으로 OCP, DIP를 지킬 수 없음 -> 구현 객체 변경 시 클라이언트 코드도 변경

목적 : 변경 최소화

### SRP - Single Responsibility Principle

> 단일 책임 원칙, 한 클래스는 하나의 책임만 가져야 함

기준 : **변경**

-> 변경이 발생했을 때, 파급효과가 적어야 함 (이상적으로, 하나의 클래스의 한 지점만 수정)

### OCP - Open Close Principle

> 개방 폐쇄 원칙, 소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 함

다형성만으로 OCP를 지킬 수 없음 -> 구현 객체(`MemberRepository`) 변경 시, 클라이언트(`MemberService`) 코드 변경 발생

```java
public class MemberService {
  //private MemberRepository memberRepository = new MemoryMemberRepository();
  private MemberRespository memberRepository = new JdbcMemberRepository(); 
}
```

무언가 더 필요하다...!

### LSP - Liscov Substitution Principle

> 리스코프 치환 원칙, 프로그램은 객체의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 함

인터페이스를 구현한 하위 클래스는 인터페이스의 규약을 지켜야 함

- 규약
  - **인터페이스에서 의도한 기능**
  - ex) 자동차의 엑셀 : 앞으로 가야함, 뒤로가면 LSP 위반

### ISP - Interface Segregation Principle

> 인터페이스 분리 원칙, 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 나음

Ex) 자동차

- 클라이언트: 운전자, 정비사
- 서버 : 운전 인터페이스, 정비 인터페이스
- **클라이언트의 담당 외 서버 인터페이스가 변해도 영향없음** (정비 인터페이스가 변해도 운전자 클라이언트에 영향 없음)

### DIP - Dependency Inversion Principle

> 의존 관게 역전 원칙, 프로그래머는 추상화에 의존해야지 구체화에 의존하면 안됌

클라이언트는 구현 클래스가 아닌, 인터페이스에 의존 -> 역할에 의존, **유연하게 구현 객체를 변경하기 위함**

다형성 만으로 DIP 지킬 수 없음 -> 추상화(인터페이스), 구체화(구현 객체) 모두에 의존 (**클라이언트가 직접 구현 객체 선택**)

```java
public class MemberService {
  //private MemberRepository memberRepository = new MemoryMemberRepository();
  private MemberRespository memberRepository = new JdbcMemberRepository(); 
}
```

무언가 더 필요하다...!
<br>
<br>
## 객체 지향 설계와 스프링

### DI 컨테이너(스프링 컨테이너)

- 다형성 + OCP, DIP 구현 가능

### 실무 고민

- 인터페이스 : 추상화 비용 발생
- 기능을 확장할 가능성이 없을 때 구체클래스 사용

