package com.dogigiri.core.loops;

public class Main {
    public static void main(String[] args) {
        int count = 1;
        do {
            System.out.println("do while ==> " + count);
            count++;
        } while (count >= 10);

        while (count <= 10) {
            System.out.println("while ==> " + count);
            count++;
        }

        for (count = 0; count < 10; count++) {
            System.out.println("for loop" + count);
        }

        int x = 0;
        for (long y = 0, z = 4; x < 5 && y < 10; x++, y++) {
            System.out.print(y + " ");
        }
        System.out.print(x + " ");

        int[] a = { 54 };
        for (int b : a) {
            System.out.println("for each " + b);
        }

        // In for loop initialization block all variables must be the same type
        for(long y = 0, x = 4; x < 5 && y < 10; x++, y++)
            System.out.print(y + " ");
        System.out.print(x); // DOES NOT COMPILE
    }
}
