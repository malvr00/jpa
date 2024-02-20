# JPA 정리

### 1.  JPA Entity 생성 및 사용법

### 1-1 Entity

#### @Entity 어노테이션을 사용해 Table을 만들어 준다.

#### Id 생성법 - @Id 어노테이션 지정, @GeneratedValue 로 Id 할당 계획 설정.

#### Column 생성법 - @Column 선언 후 name 옵션으로 column 명 지정. 지정 안할 시 변수 명으로 생성된다. 그리고 @Column에는 많은 옵션이 있다. Column 명 지정, 길이, null 체크 등.

### 1-2 JPA 실행

#### 트랜잭션이 일어나는 단위가 발생 할 때 먼저 EntityManager를 만들어주고 사용해야한다.

#### EntityManager로 EntityTracnsaction을 만들어줘서 트랜잭션 수행하기 위한 변수를 만들어준다.

#### JPA 업데이트는 DB에서 데이터를 JPA를 통해 찾은 후 찾은 값의 값만 변경 시키면 rx.commit() 시점에 자동으로 업데이트 쿼리가 발생한다.

#### 엔티티 매니저는 쓰레드간 공유하면 안됨

#### JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
