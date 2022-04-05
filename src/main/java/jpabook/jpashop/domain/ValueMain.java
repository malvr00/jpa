package jpabook.jpashop.domain;

public class ValueMain {
    public static void main(String[] args) {


        Integer a1 = 10;
        Integer b1 = a1;

        a1 = 20;

        int a = 10;
        int b = a;

        a = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        System.out.println("a1 = " + a1);
        System.out.println("b1 = " + b1);

    }
}
