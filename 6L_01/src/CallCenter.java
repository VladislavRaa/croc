import java.util.*;

public class CallCenter {
    public void countLog(ArrayList<String> log, int value) {
        ArrayList<String[]> operators = new ArrayList<>();
        for (String operator : log) {
            operators.add(operator.split(","));
        }
        operators.sort((o1, o2) -> {
            int one = Integer.valueOf(o1[1]) - Integer.valueOf(o1[0]);
            int two = Integer.valueOf(o2[1]) - Integer.valueOf(o2[0]);
            return Integer.compare(two, one);
        });
        for (int i = 0; i < value; i++) {
            System.out.println(operators.get(i)[0] + ", " + operators.get(i)[1] + ", " + operators.get(i)[2]);
        }
    }
}