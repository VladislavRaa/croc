import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> log = new ArrayList<String>();
        log.add("1,121,operator1");
        log.add("4,13,operator2");
        log.add("7,12,operator2");
        log.add("10,32,operator3");
        log.add("15,30,operator1");
        log.add("10,31,operator4");
        log.add("10,111,operator4");
        log.add("10,30,operator1");

        CallCenter cc = new CallCenter();
        cc.countLog(log);
    }
}