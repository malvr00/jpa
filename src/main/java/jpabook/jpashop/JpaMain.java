package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 저장

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);   // Id 에 값이 들어감 ( 영속성 상태 )

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member);
//            team.addMember(member);
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("=======================");
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("=======================");




            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
