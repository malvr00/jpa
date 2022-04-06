package jpabook.jpashop.jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team team1 = new Team();
            team1.setName("teamB");
            em.persist(team1);

            Member member = new Member();
            member.setUsername("회원1");
            member.setAge(10);
            member.changeTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(10);
            member2.changeTeam(team1);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(10);
            member3.changeTeam(team);
            member3.setType(MemberType.ADMIN);
            em.persist(member3);

            // 벌크연산 = update delete 문
            // executeUpdate 벌크 연산 주의
            // 벌크 연산은 영속성 컨텍스트를 무시하고 데이터 베이스에 직접 쿼리 발송
            // 해결 방안 : 1. 벌크 연산을 먼저실행, 2. 벌크 연산 수행 후 영속성 컨텍스트 초기화
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            System.out.println("findMember = " + findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();

    }
}
