import java.io.*;

public class Main {

    public static void main(String[] args) {
        String filename = args[0];
        cutComments(filename);
    }

    public static void cutComments(String filename) {
        final String multiLineCommets = "/\\*+[^*]*\\*+(?:[^/*][^*]*\\*+)*/";
        final String lineCommets = "//.*$";
        StringBuilder resultString = new StringBuilder();

        try (BufferedReader r = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = r.readLine()) != null) {
                resultString.append(line.replaceAll(lineCommets, "") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resultString.toString().replaceAll(multiLineCommets, " "));
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            w.write(resultString.toString().replaceAll(multiLineCommets, " "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
