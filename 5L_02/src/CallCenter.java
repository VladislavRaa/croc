import java.util.ArrayList;
import java.util.HashSet;

public class CallCenter {
    public float countLog(ArrayList<String> log) {
        String[] tempString;
        float sum = 0;
        HashSet<String> operators = new HashSet<String>();
        for (String operator : log) {
            tempString = operator.split(",");
            operators.add(tempString[2]);
            sum += Integer.valueOf(tempString[1]) - Integer.valueOf(tempString[0]);
        }
        return Math.round(sum / operators.size());
    }
}
