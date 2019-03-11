public class Main {

    public static void main(String[] args) {
        Tree test = new Tree();
        createHierarchy(3, 3, test);
    }

    public static void createHierarchy(int deep, int width, Tree tree) {

        Character[] patern = {'A', 'B', 'C', 'D', 'E', 'F'};
        int k = 1;
        tree.createRoot(0, "A");
        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), i, patern[k].toString() + " " + i);
        }

        tree.NextToFirsdChild(tree.getParent());


        k++;
        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), i + width, patern[k].toString());
        }

        tree.setParent(tree.next(tree.getParent()));
        System.out.println(tree.getParentName());

        k++;
        for (int i = 0; i < width; i++) {
            tree.addChildren(tree.getParent(), i + width, patern[k].toString());
        }


    }

}
