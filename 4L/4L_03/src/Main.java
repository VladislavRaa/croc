import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        System.out.println(getPeakAmount(filename));
    }

    public static int getPeakAmount(String filename) throws FileNotFoundException {
        String line;
        List<int[]> resultOfRead = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String[] temp;
            while ((line = in.readLine()) != null) {
                temp = line.split(",");
                if (temp.length != 2) {
                    throw new IllegalArgumentException("wrong format");
                }
                resultOfRead.add(new int[]{parseInt(temp[0]), parseInt(temp[1])});
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return findPeak(resultOfRead);
    }

    private static int findPeak(List<int[]> resultOfRead) {
        final int INDEX_LAST_ELEMENT_COLUMN = 1;
        final int INDEX_FIRST_ELEMENT_COLUMN = 0;
        final int INDEX_LAST_ELEMENT_ROW = resultOfRead.size() - 1;
        int start = resultOfRead.get(0)[0];
        int finish = resultOfRead.get(INDEX_LAST_ELEMENT_ROW)[INDEX_LAST_ELEMENT_COLUMN];
        int entry = 0;
        int result = 0;

        while (start < finish) {
            for (int i = 0; i <= INDEX_LAST_ELEMENT_ROW; i++) {
                if (isInclude(new int[]{resultOfRead.get(i)[INDEX_FIRST_ELEMENT_COLUMN], resultOfRead.get(i)[INDEX_LAST_ELEMENT_COLUMN]}, start)) {
                    entry++;
                    if (entry > result) {
                        result = entry;
                    }
                }
            }
            start++;
            entry = 0;
        }
        return result;
    }

    private static boolean isInclude(int[] segment, int element) {
        for (int i = segment[0]; i <= segment[1]; ++i) {
            if (element == i) {
                return true;
            }
        }
        return false;
    }
}
