import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> log1 = new ArrayList<String>();
        log1.add("1,12,operator1");
        log1.add("4,6,operator2");
        log1.add("4,6,operator3");
        log1.add("4,6,operator4");

        ArrayList<String> log2 = new ArrayList<String>();
        log2.add("1,12,operator1");
        log2.add("4,6,operator2");
        log2.add("4,6,operator3");
        log2.add("4,6,operator4");
        log2.add("4,6,operator5");

        ArrayList<String> log3 = new ArrayList<String>();
        log3.add("7,12,operator1");
        log3.add("7,12,operator2");
        log3.add("7,12,operator3");
        log3.add("7,12,operator4");
        log3.add("10,32,operator5");


        CallCenter cc1 = new CallCenter(log1);
        CallCenter cc2 = new CallCenter(log2);
        CallCenter cc3 = new CallCenter(log3);
        cc1.start();
        cc2.start();
        cc3.start();

        /*System.out.println(cc1.beforeAllThread());
        System.out.println(cc2.beforeAllThread());
        System.out.println(cc2.beforeAllThread());*/
    }
}

