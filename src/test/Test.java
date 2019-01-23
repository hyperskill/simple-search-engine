package test;

/**
 * Created by DIMA, on 21.01.2019
 */
public class Test {
    public static void main(String[] args) {
        String a = " df df    defdf ";
        System.out.println(a.trim().split(" +").length);
        System.out.println(a.split(" ").length);
    }
}
