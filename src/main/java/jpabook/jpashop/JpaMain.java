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
            Member member2 = new Member();

            Address address = new Address("test", "test", "test");
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

//            member.setName("test");
//            member2.setName("test2");
//            member.setHomeAddress(address);
            // member.setHomeAddress(address);  // 이렇게 사용하면 안됌. member, member2 가 같은 주소값인 address 를 참조하고 있어서
            //                                     address 의 인스턴스 멤버 값을 변경하면 member, member2 모두 업데이트 쿼리가 실행됌
            //                                     공유 자원의 문제가 발생함
//            member2.setHomeAddress(copyAddress);

            em.persist(member);
//            em.persist(member2);

//            member.getHomeAddress().setCity("newCity");

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
