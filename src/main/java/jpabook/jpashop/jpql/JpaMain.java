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

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.changeTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자");
            member2.setAge(10);
            member2.changeTeam(team);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);
            em.flush();
            em.clear();

//            String sql = "select concat('a', 'b') from Member m";
//            String sql = "select substring(m.username, 2,3) from Member m";
//            String sql = "select locate('de','abcdefg') from Member m";
//            String sql = "select size(t.members) from Team t";
            String sql = "select function('group_concat', m.username) from Member m";
            List<String> query = em.createQuery(sql, String.class).getResultList();
            for (String s : query) {
                System.out.println("s = " + s);
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
