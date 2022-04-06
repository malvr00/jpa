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

            String sql = "select t From Team t";
            // 절때 쓰면 안됌 메모리에서 페이징함
            // 1:N 에서 사용하면 안됌
//            List<Team> query = em.createQuery(sql, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(1)
//                    .getResultList();
            List<Team> query = em.createQuery(sql, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
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
