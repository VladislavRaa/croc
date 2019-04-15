import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallCenter extends Thread {
    private static ConcurrentHashMap<String, Integer> operators = new ConcurrentHashMap<>();
    private List<String> log;

    CallCenter(List<String> log) {
        this.log = log;
    }

    public static synchronized void getBestWorker(List<String> log) {
        String[] tempString;
        for (String operator : log) {
            tempString = operator.split(",");
            if (operators.containsKey(tempString[2])) {
                operators.put(tempString[2], operators.get(tempString[2]) + 1);
            } else {
                operators.put(tempString[2], 1);
            }
        }
    }

    public static String beforeAllThread() {
        ArrayList<Integer> byTotalCall = new ArrayList<>(operators.values());
        byTotalCall.sort(Integer::compare);
        return getKeyFromValue(operators, byTotalCall.get(byTotalCall.size() - 1));
    }

    private static String getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o.toString();
            }
        }
        return null;
    }

    @Override
    public void run() {
        getBestWorker(this.log);
    }
}
