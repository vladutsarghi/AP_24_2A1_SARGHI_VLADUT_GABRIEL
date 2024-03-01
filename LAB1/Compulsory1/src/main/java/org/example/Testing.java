package org.example;
public class Testing {

    public static void main(String[] args) {
        String limbaje[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        for (int i = 0; i < 10; i++) {
            System.out.println(limbaje[i]);
        }

        int n = (int) (Math.random() * 1_000_000);
        System.out.println(n);

        n *= 3;
        System.out.println(n);

        n += 0b10101;
        System.out.println(n);

        n += Integer.parseInt("FF", 16);
        System.out.println(n);

        n *= 6;
        System.out.println(n);

        while (n > 9) {
            int copy = 0;
            while (n != 0) {
                copy += n % 10;
                n /= 10;
            }
            n = copy;
        }
        String output = "Willy-nilly, this semester I will learn " + limbaje[n];

        System.out.println("Willy-nilly, this semester I will learn ");
        System.out.println(limbaje[n]);

        System.out.println(output);
    }
}
