package jpabook.jpashop.jpql;

import javax.persistence.*;

@Entity
// 정적 쿼리
// 미리 정의해서 이름을 부여해두고 사용하는 JPQL
// 어노테이션, XML에 정의 XML 우선권 가짐
// 애플리케이션 로딩 시점에 초기화 후 재사용
//  * 애플리케이션 로딩 시점에 쿼리를 검증
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public void changeTeam (Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
