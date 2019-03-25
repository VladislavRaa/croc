import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];
        System.out.println(countWords(filename));
    }

    public static int countWords(String filename) {
        int oneByte;
        int notChar = 0;
        int isChar = 0;
        int res = 0;
        try (InputStream in = new BufferedInputStream(new FileInputStream(filename))) {
            while ((oneByte = in.read()) != -1) {
                if (oneByte == ' ' || oneByte == '\n') {
                    notChar++;
                    isChar = 0;
                } else {
                    isChar++;
                    notChar = 0;
                }
                if (notChar == 0 && isChar == 1) {
                    res++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
