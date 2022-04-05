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
            member.setHomeAddress(new Address("test", "test", "test"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("test1", "test1", "test1"));
            member.getAddressHistory().add(new AddressEntity("test2", "test2", "test2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========================================");
            Member findMember = em.find(Member.class, member.getId());

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("사과");

            // 값 타입 컬렉션의 제약사항
            // 값은 변경하면 추적이 어렵다.
            // 값 타입 컬렉션에 변경 사항이 발생하면, 주인 엔티티와 연관된 모든 데이터를 삭제하고, 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장
            // 값 타입 컬렉션을 매핑하는 테이블은 모든 컬럼을 묶어서 기본키를 구성해야함 : Null 입력 X , 중복 저장 X
            // 대도록 사용하면 안됌
//            findMember.getAddressHistory().remove(new Address("test1", "test1", "test1"));
//            findMember.getAddressHistory().add(new Address("test4", "test1", "test1"));

            // 대안
            List<AddressEntity> list = findMember.getAddressHistory();
            for (AddressEntity addressEntity : list) {
                if(addressEntity.getAddress().getCity().equals("test1")){
                    addressEntity.setAddress(new Address("test4", addressEntity.getAddress().getStreet(), addressEntity.getAddress().getZipcode()));
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
