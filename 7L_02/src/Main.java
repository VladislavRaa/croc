import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> log = new ArrayList<>();
        log.add("/Users/vladislavraa/croc/7L_02/test1.txt");
        log.add("/Users/vladislavraa/croc/7L_02/test2.txt");
        log.add("/Users/vladislavraa/croc/7L_02/test3.txt");
        log.add("/Users/vladislavraa/croc/7L_02/test4.txt");
        log.add("/Users/vladislavraa/croc/7L_02/test5.txt");
        log.add("/Users/vladislavraa/croc/7L_02/test6.txt");
        log.add("/Users/vladislavraa/croc/7L_02/test7.txt");
        CallCenter test = new CallCenter();
        System.out.println("winners: " + test.getBestWorker(log));
    }

}
