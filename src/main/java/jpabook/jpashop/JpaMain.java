package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // Criteria 사용 준비
            // 유지 보수 힘들어서 잘 안씀
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // 사용법
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m);
            cq.where(cb.equal(m.get("username"), "kim"));

            em.createQuery(cq).getResultList();
// ===============================================================
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            // flush -> commit, query
            // dbconn.excuteQuery("select * from member"); <- jpa 가 아니라서 flush 가 안됌
            // 그래서 강제로 em.flush() 실행 시켜줌

            // 네이티브 쿼리
            // 적절한 시점에 강제로 플러시 필요
            em.createNativeQuery("SELECT MEMBER_ID, city, street FROM MEMBER", Member.class).getResultList();


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
