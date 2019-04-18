import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CallCenter implements Runnable {
    private static volatile ConcurrentHashMap<String, Integer> operators = new ConcurrentHashMap<>();
    private static volatile Iterator iterator;
    private final static int MAX_THREADS = 7;
    private static ArrayList<Thread> arrThreads = new ArrayList<>();

    public String getBestWorker(List<String> log) throws InterruptedException {
        iterator = log.iterator();
        for (int i = 0; i < MAX_THREADS; i++) {
            Thread currentThread = new Thread(new CallCenter());
            currentThread.start();
            arrThreads.add(currentThread);
        }
        for (int i = 0; i < MAX_THREADS; i++) {
            arrThreads.get(i).join();
        }
        return startBefore();
    }

    private static void getLog(String pathOfLog) {
        System.out.println(Thread.currentThread().getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(pathOfLog))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tempString = line.split(",");
                synchronized (operators) {
                    if (operators.containsKey(tempString[2])) {
                        operators.put(tempString[2], operators.get(tempString[2]) + 1);
                    } else {
                        operators.put(tempString[2], 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String startBefore() {
        ArrayList<Integer> byTotalCall = new ArrayList<>(operators.values());
        byTotalCall.sort(Integer::compare);
        StringBuilder result = new StringBuilder();
        int count = byTotalCall.size() - 1;
        int size = byTotalCall.size() - 1;
        while (byTotalCall.get(size).equals(byTotalCall.get(count))) {
            result.append(getKeyFromValue(operators, byTotalCall.get(count))).append(" ");
            count--;
        }
        return result.toString();
    }

    private static String getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                String resultString = o.toString();
                hm.remove(o);
                return resultString;
            }
        }
        return null;
    }

    @Override
    public void run() {
        if (iterator.hasNext()) {
            getLog(iterator.next().toString());
        }
    }
}
