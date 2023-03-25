package com.dogigiri.core.string;

public class StringPoolDemo {
    public static void main(String[] args) {
        String one = "hello";
        String two = "hello";
        System.out.println(compareReference(one, two));
        one = "Bye";
        System.out.println(compareReference(one, two));

        String castNum = String.valueOf(75L).intern();
        String num = "75";
        System.out.println(compareReference(castNum, num));
    }

    private static boolean compareReference(String literalOne, String literalTwo) {
        return literalOne == literalTwo;
    }
}
