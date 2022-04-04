package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("member1");

            em.persist(member);

            em.flush();
            em.clear();

            Member ref = em.getReference(Member.class, member.getId());
            System.out.println("ref = " + ref.getClass());  // Proxy
//            ref.getName();

            Hibernate.initialize(ref);  // 강제 초기화
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(ref)); // 프록시 인스턴스의 초기화 여부 확인

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
