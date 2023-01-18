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
        System.out.println(getDigital(str));
        //StringBuilder unitSequence = new StringBuilder();
//        for (int i = 0; i < str.length(); i++) {
//            String binarySequence = String.format("%07d", Integer.parseInt(getBin(str.charAt(i))));
//            //System.out.println(binarySequence);
//            unitSequence.append(binarySequence);
//            }
//        System.out.print(getBinaryCyfrated(String.valueOf(unitSequence)));
//        }
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

    public static String getBinaryCyfrated(String binarySequence1) {
        String binarySequence = binarySequence1 + " ";
        int count = 1;
        StringBuilder zeroEncripted = new StringBuilder();
        for (int j = 0; j < binarySequence.length() - 1; j++) {
            if (binarySequence.charAt(j) == binarySequence.charAt(j + 1)) {
                count++;
            } else {
                if (binarySequence.charAt(j) == '1') {
                    zeroEncripted.append("0 ");
                    for (int i = 0; i < count; i++) {
                        zeroEncripted.append("0");
                    }
                    zeroEncripted.append(" ");
                    count = 1;
                } else {
                    zeroEncripted.append("00 ");
                    for (int i = 0; i < count; i++) {
                        zeroEncripted.append("0");
                    }
                    zeroEncripted.append(" ");
                    count = 1;
                }
            }

        }
        zeroEncripted.deleteCharAt(zeroEncripted.length() - 1);
        return zeroEncripted.toString();
    }

    public static String getDigital(String str) {
        StringBuilder binaryString = new StringBuilder();
        StringBuilder decoded = new StringBuilder();
        String[] splitArray = str.split(" ");
        int odd  = 1;
        for (int i = 0; i < splitArray.length; i += 2) {
            if (splitArray[i].equals("0")){
                binaryString.append("1".repeat(splitArray[odd].length()));
            } else if (splitArray[i].equals("00")) {
                binaryString.append("0".repeat(splitArray[odd].length()));
            }
            odd += 2;
        }

        int count = 0;
        StringBuilder cmt = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i++) {
            count++;
            cmt.append(binaryString.charAt(i));
            if (count == 7) {
                decoded.append(bitToInt(cmt.toString()));
                cmt.delete(0,cmt.length());
                count = 0;
            }
        }
        return decoded.toString();
    }

    public static String bitToInt(String bin) {
        StringBuilder decodedLine = new StringBuilder();
        int decimalNumber = 0;
        double power = 0;
        char[] charArray = bin.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            decimalNumber += Integer.parseInt(String.valueOf(charArray[i])) * Math.pow(2, power);
            power++;
        }
        decodedLine.append((char) decimalNumber);

        return decodedLine.toString();
    }
}