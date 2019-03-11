import java.util.ArrayList;

public class Tree {
    private Node root; // больше не трогать
    private Node parent;

    Tree() {
        this.root = new Node();
        this.parent = new Node();
    }

    public class Node {
        private String name;
        private int time;
        private Node parentNode;
        private ArrayList<Node> children;
        private int capacity;

        Node(){}

        Node(int time, String nameOfDepartment) {
            this.name = nameOfDepartment;
            this.parentNode = parent;
            this.time = time;
            this.children = new ArrayList<Node>();
        }
    }

    public Node createRoot(int time, String nameOfDepartment) {
        Node root = new Node(time, nameOfDepartment);
        root.capacity = 0;
        root.parentNode = null;
        this.root = root;
        this.parent = this.root;
        return this.parent;
    }

    public Node addChildren(Node parent, int time, String nameOfDepartment) {
        Node child = new Node(time, nameOfDepartment);
        parent.capacity += 1;

        child.parentNode = this.parent;

        this.parent.children.add(child);
        return this.parent;
    }

    public Node addLeaf(Node parent, int time, String nameOfDepartment) {
        Node child = new Node(time, nameOfDepartment);
        parent.capacity += 1;

        child.parentNode = this.parent;

        this.parent.children.add(child);
        this.parent = child;
        return child;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getRoot() {
        return this.root;
    }

    void printRoot() {
        System.out.println(this.parent.time);
    }

    void printCapacity() {
        System.out.println("Capacity: " + this.parent.capacity);
    }

    void printChildren() {
        for(Node i : getParent().children) {
            System.out.println("name: " + i.name + " time: " + i.time);
        }
        System.out.println("-----end-------");
    }

    public int count(Node obj) {
        return obj.parentNode.children.size();
    }

    void printParent(Node obj) {
        System.out.println(obj.parentNode.name + " is parent: " +  obj.name);
    }

    public void itter(Node obj) {
        for(Node i : obj.children) {
            System.out.print(i.time + " ");
            if (!i.children.isEmpty()) {
                itter(i);
            }
        }
    }
}

