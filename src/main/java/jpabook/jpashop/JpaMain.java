package jpabook.jpashop;

import jpabook.jpashop.domain.*;

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
            Order order = em.find(Order.class, 6L);

            List<OrderItem> orderItems = order.getOrderItems();

            System.out.println("===========================");
            for (OrderItem o : orderItems) {
                System.out.println(" o => " + o.getId());
            }
            System.out.println("===========================");
//            Member member = new Member();
//            member.setName("memberA");
//            member.setCity("a");
//            member.setStreet("a");
//            member.setZipcode("1");
//
//            em.persist(member);
//
//            Order order = new Order();
//            order.setMember(member);
//            order.setStatus(OrderStatus.ORDER);
//            em.persist(order);
//
//            Item item = new Item();
//            item.setName("Test");
//            item.setPrice(111);
//            item.setStockQuantity(10);
//            em.persist(item);
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setItem(item);
//            em.persist(orderItem);
//
//            order.addOrderItem(orderItem);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }
}
