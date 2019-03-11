public class Main {

    public static void main(String[] args) {

        Tree test = new Tree();

        test.createRoot(777, "A");
        test.addLeaf(test.getParent(), 1, "B");
        test.addChildren(test.getParent(),2, "C");
        test.printParent(test.getParent());






    }


}
