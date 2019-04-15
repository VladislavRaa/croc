import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> log1 = new ArrayList<String>();
        log1.add("1,12,operator1");
        log1.add("4,6,operator4");///
        log1.add("4,6,operator3");//
        log1.add("4,6,operator3");//
        log1.add("4,6,operator5");

        ArrayList<String> log2 = new ArrayList<String>();
        log2.add("1,12,operator4");///
        log2.add("4,6,operator2");
        log2.add("4,6,operator3");//
        log2.add("4,6,operator4");
        log2.add("4,6,operator5");

        ArrayList<String> log3 = new ArrayList<String>();
        log3.add("7,12,operator6");
        log3.add("7,12,operator7");
        log3.add("7,12,operator8");
        log3.add("7,12,operator9");
        log3.add("10,32,operator10");

        ArrayList<String> log4 = new ArrayList<String>();
        log4.add("7,12,operator6");
        log4.add("7,12,operator7");
        log4.add("7,12,operator8");
        log4.add("7,12,operator9");
        log4.add("10,32,operator10");
        ArrayList<String> log5 = new ArrayList<String>();
        log5.add("7,12,operator4");///
        log5.add("7,12,operator5");
        log5.add("7,12,operator1");
        log5.add("7,12,operator3");//
        log5.add("10,32,operator3");//
        ArrayList<String> log6 = new ArrayList<String>();
        log6.add("7,12,operator4");///
        log6.add("7,12,operator1");
        log6.add("7,12,operator5");
        log6.add("7,12,operator1");
        log6.add("10,32,operator1");

        ArrayList<String> log7 = new ArrayList<String>();
        log6.add("7,12,operator4");///
        log6.add("7,12,operator1");
        log6.add("7,12,operator5");
        log6.add("7,12,operator1");
        log6.add("10,32,operator1");

        CallCenter cc1 = new CallCenter(log1);
        CallCenter cc2 = new CallCenter(log2);
        CallCenter cc3 = new CallCenter(log3);
        CallCenter cc4 = new CallCenter(log4);
        CallCenter cc5 = new CallCenter(log5);
        CallCenter cc6 = new CallCenter(log6);
        CallCenter cc7 = new CallCenter(log7);

        cc1.start();
        cc2.start();
        cc3.start();
        cc4.start();
        cc5.start();
        cc6.start();
        cc7.start();

        cc1.join();
        cc2.join();
        cc3.join();
        cc4.join();
        cc5.join();
        cc6.join();
        cc7.join();

        System.out.println(CallCenter.beforeAllThread());
    }

}
