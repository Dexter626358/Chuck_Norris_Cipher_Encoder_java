package chucknorris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input string: ");
        String str = scan.nextLine();
        System.out.println("The result:");
        for (int i = 0; i < str.length(); i++) {
            System.out.printf("%c = %07d\n", str.charAt(i), Integer.parseInt(getBin(str.charAt(i))));

        }
    }

    public static String getBin(char charecter) {
        int number = charecter;
        ArrayList<Integer> binary = new ArrayList<>();
        String result = "";
        while (number != 0) {
            binary.add(number % 2);
            number /= 2;
        }
        for (int i = binary.size() - 1; i >= 0 ; i--) {
            result += binary.get(i).toString();
        }


        return result;
    }
}