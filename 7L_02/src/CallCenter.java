import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CallCenter extends Thread {
    private boolean cancelled;
    private volatile ConcurrentHashMap<String, Integer> operators = new ConcurrentHashMap<>();
    private List<String> log;

    CallCenter(List<String> log) {
        this.log = log;
    }

    public synchronized String getBestWorker(List<String> log) {
        String[] tempString;
        for (String operator : log) {
            tempString = operator.split(",");
            synchronized (operator) {


                if (operators.containsKey(tempString[2])) {
                    operators.putIfAbsent(tempString[2], operators.get(tempString[2]) + 1);
                    System.out.println("update: " + tempString[2] + "," + operators.get(tempString[2]));
                } else {
                    operators.putIfAbsent(tempString[2], 1);
                    System.out.println("add: " + tempString[2] + "," + operators.get(tempString[2]));
                }
            }
        }
        //cancel();
        return beforeAllThread();
    }

    public void cancel() {
        cancelled = true;
    }

    public String beforeAllThread() {
        ArrayList<Integer> byTotalCall = new ArrayList<>(operators.values());
        byTotalCall.sort(Integer::compare);
        String res = getKeyFromValue(operators, byTotalCall.get(byTotalCall.size() - 1));

        System.out.println(res + " " + Thread.currentThread().getName());
        return res;
    }

    private String getKeyFromValue(Map hm, Object value) {
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