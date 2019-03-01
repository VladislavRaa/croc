import java.util.Scanner;

public class Main {

    public static StringBuilder reverseМowels(StringBuilder inputStr) {
        StringBuilder strChange = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i++) {
            if (isМowels(inputStr.charAt(i))) {
                strChange.append(inputStr.charAt(i));
                inputStr.setCharAt(i, '*');
            }
        }
        strChange.reverse();
        for (int i = 0, j = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) == '*') {
                inputStr.setCharAt(i, strChange.charAt(j));
                j++;
            }
        }
        return inputStr;
    }

    public static boolean isМowels(Character inputStr) {
        char[] patern = {'a', 'e', 'i', 'o', 'u', 'y'};
        for (char i : patern) {
            if (inputStr.equals(i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder str = new StringBuilder(in.nextLine());
        System.out.println(reverseМowels(str));
    }
}
