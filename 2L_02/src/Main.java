public class Main {

    public static void main(String[] args) {
        Tree test = new Tree();
        createHierarchy(3, 4, test);
        //test.printRoot();
        test.itter(test.getRoot());
        System.out.println("smallest time: " + test.getMinTime());
    }

    public static void createHierarchy(int deep, int width, Tree tree) {

        Character[] patern = {'A', 'B', 'C', 'D', 'E', 'F'};
        int k = 1;
        tree.createRoot(11, "A");
        //add B
        for (int i = 0; i < 3; i++) {
            tree.addChildren(tree.getParent(), k + i + 10, patern[k].toString() + i);
        }
        //B0
        tree.NextToFirstChild(tree.getParent());
        k++;
        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), k + i, patern[k].toString() + i);
        }

        //B1
        tree.setParent(tree.next(tree.getParent(), 1));

        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), i + k, patern[k].toString() + i);
        }
        tree.addChildren(tree.getParent(), 0,patern[k].toString() + 11);

        //B2
        tree.setParent(tree.next(tree.getParent(), 2));

        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), i + k, patern[k].toString() + i);
        }
        //C
        tree.NextToFirstChild(tree.getParent());
        k++;
        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), k + i, patern[k].toString() + i);
        }
        tree.addChildren(tree.getParent(), 0,patern[k].toString() + 11);
    }
}
