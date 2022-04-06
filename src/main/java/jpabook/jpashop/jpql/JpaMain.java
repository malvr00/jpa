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

            em.flush();
            em.clear();

//            String sql = "select m from Member m join fetch m.team";
//            String sql = "select t From Team t join fetch t.members";   // 컬랙션으로 select 할 경우 중복제거가 안됌 (데이터 뻥튀기 조심)
//            String sql = "select distinct t From Team t join fetch t.members";  // jpa 에서 같은 엔티티 제거 해줌 sql 이랑 다르게 동작
            String sql = "select t From Team t join t.members";
            List<Team> query = em.createQuery(sql, Team.class).getResultList();
            for (Team s : query) {
                System.out.println("member = " + s.getName() + ", " + s.getMembers().size());
                for(Member m : team.getMembers()){
                    System.out.println(" -> member = " + m);
                }
            }

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
