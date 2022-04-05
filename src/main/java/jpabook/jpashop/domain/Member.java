package jpabook.jpashop.domain;

import javax.persistence.*;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    // 기간
    @Enumerated
    private Period workPeriod;

    // 주소
    @Enumerated
    private Address homeAddress;

    // 주소
    @Enumerated
    @AttributeOverrides({
            @AttributeOverride(name = "city", column=@Column(name = "work_city")),
            @AttributeOverride(name = "street", column=@Column(name = "work_street")),
            @AttributeOverride(name = "zipcode", column=@Column(name = "work_zipcode")),
    })
    private Address workAddress;

    // 실무에선 즉시로딩 사용하면 안됌. 가극적 지연로딩만 사용해야한다
    // 1. 즉시 로딩 적용하면 예상하지 못한 sql 발생
    // 2. 즉시 로딩은 JPQL 에서 N+1 문제를 이르킨다.
    // 3. ManyToOne, OneToOne 은 기본이 즉시 로딩
    // 4. OneToMany 기본이 지연 로딩
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }

//    public List<MemberProduct> getMemberProducts() {
//        return memberProducts;
//    }
//
//    public void setMemberProducts(List<MemberProduct> memberProducts) {
//        this.memberProducts = memberProducts;
//    }
}
