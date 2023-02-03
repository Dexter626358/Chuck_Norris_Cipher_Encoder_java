package chucknorris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scan.nextLine();
            if (operation.equals("encode")) {
                System.out.println("Input string: ");
                String str = scan.nextLine();
                StringBuilder unitSequence = new StringBuilder();
                for (int i = 0; i < str.length(); i++) {
                    String binarySequence = String.format("%07d", Integer.parseInt(getBin(str.charAt(i))));
                    unitSequence.append(binarySequence);
                }

                System.out.println(getBinaryCyfrated(String.valueOf(unitSequence)));
                System.out.println();
            }else if (operation.equals("decode")) {
                System.out.println("Input encoded string:");
                String str = scan.nextLine();

                System.out.println(getDigital(str));
                System.out.println();
            } else if (operation.equals("exit")) {
                System.out.println("Bye!");
                break;
            }else {
                System.out.println(String.format("There is no '%s' operation", operation));
                System.out.println();
            }
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
        System.out.println("Encoded string:");
        return zeroEncripted.toString();
    }

    public static String getDigital(String str) {
        StringBuilder binaryString = new StringBuilder();
        StringBuilder decoded = new StringBuilder();
        StringBuilder withoutGaps = new StringBuilder();
        String[] splitArray = str.split(" ");
        for (int i = 0; i < splitArray.length; i+=2) {
            if (splitArray[i].length() > 2) {
                return "Encoded string is not valid.";
            }
        }
        for (String item: splitArray) {
            withoutGaps.append(item);
        }
        String arrayString = Arrays.toString(splitArray);
        String withoutGapsStr = withoutGaps.toString();
        char[] arrayChar = new char[withoutGapsStr.length()];
        for (int i = 0; i < withoutGapsStr.length(); i++ ) {
            arrayChar[i] = withoutGapsStr.charAt(i);
        }

        for (char item: arrayChar) {
            if (item != '0') {
                return "Encoded string is not valid.";
            }
        }

        if (splitArray.length % 2 != 0) {
            return "Encoded string is not valid.";
        }


        for (int i = 0; i < splitArray.length; i += 2) {
            if (splitArray[i].equals("0")){
                binaryString.append("1".repeat(splitArray[i+1].length()));
            } else if (splitArray[i].equals("00")) {
                binaryString.append("0".repeat(splitArray[i+1].length()));
            }

        }
        if (binaryString.length() % 7 != 0) {
            return "Encoded string is not valid.";
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
        System.out.println("Decoded string:");
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