package jpabook.jpashop.domain;

public class ValueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("a ==  b" + (a == b));

        Address address1 = new Address("test","test","test");
        Address address2 = new Address("test","test","test");

        System.out.println(address1.equals(address2));
    }
}
