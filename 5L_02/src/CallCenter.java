import java.util.ArrayList;

public class CallCenter {
    public float countLog(ArrayList<String> log) {
        String[] tempString;
        String pattern = "[^0-9]";
        float sum = 0;
        int maxOperator = 0;
        int tempMaxOperator = 0;
        for (String operator : log) {
            tempString = operator.split(",");
            tempMaxOperator = Integer.valueOf(tempString[2].replaceAll(pattern, ""));
            if (tempMaxOperator > maxOperator) {
                maxOperator = tempMaxOperator;
            }
            sum += Integer.valueOf(tempString[1]) - Integer.valueOf(tempString[0]);
        }
        return Math.round(sum / maxOperator);
    }
}
