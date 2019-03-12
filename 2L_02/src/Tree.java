import java.util.ArrayList;

public class Tree {
    private Node root; // больше не трогать
    private Node parent;
    private StringBuilder result = new StringBuilder();
    private int count = 0;
    private int sum = 0;

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

        Node() {
        }

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
    } //return old parent

    public Node addLeaf(Node parent, int time, String nameOfDepartment) {
        Node child = new Node(time, nameOfDepartment);
        parent.capacity += 1;

        child.parentNode = this.parent;

        this.parent.children.add(child);
        this.parent = child;
        return child;
    } //return new parent

    public Node getParent() {
        return this.parent;
    } //текущий родитель

    public String getParentName() {
        return this.parent.name;
    }

    public Node getRoot() {
        return this.root;
    }

    void printRoot() {
        System.out.println(this.root.name + ":" + this.root.time);
    }

    public void printCapacity() {
        System.out.println("Capacity: " + this.parent.capacity);
    }

    public void printChildren() {
        for (Node i : getParent().children) {
            System.out.println("name: " + i.name + " time: " + i.time);
        }
        System.out.println("-----end-----");
    }

    public void printParent(Node obj) {
        System.out.println(obj.parentNode.name + " is parent: " + obj.name);
    }

    public void printNextToChildren(Node obj) {
        for (Node i : obj.parentNode.children) {
            System.out.println(i.name);
        }
    }

    public void printAllParents(Node obj) {
        if (obj.parentNode != null) {
            System.out.print("[n:" + obj.parentNode.name + " t:" + obj.parentNode.time + "] ");
            printAllParents(obj.parentNode);
        }
    }


    public StringBuilder parentsToString(Node obj) { //
        if (obj.parentNode != null) {
            this.result.insert(this.count, "[n:" + obj.parentNode.name + " t:" + obj.parentNode.time + "], ");
            this.count++;
            parentsToString(obj.parentNode);
        }
        this.count = 0;
        return result;
    }

    public void cleanResultString() {
        this.result = new StringBuilder();
    }

    public int parentsSum(Node obj) {
        if (obj.parentNode != null) {
            this.sum += obj.parentNode.time;
            parentsSum(obj.parentNode);
        }
        return this.sum;
    }

    public int count(Node obj) {
        return obj.parentNode.children.size();
    }

    public Node NextToChildren(Node obj) {
        for (Node i : obj.parentNode.children) {
            return i;
        }
        return null;
    }

    static Node next(Node obj, int count) {                             //нужно переделть!!!!
        /*if(obj.parentNode.children.iterator().hasNext()) {
            return obj.parentNode.children.iterator().next();
        } else
            return null;*/
        return obj.parentNode.children.get(count);
    }

    public void setParent(Node obj) {
        this.parent = obj;
    }

    public Node NextToFirstChild(Node obj) {
        if (obj.children.isEmpty()) {
            return null;
        }
        setParent(obj.children.get(0));
        return this.parent;
    }

    public Node BackToFirsdChild(Node obj) {
        return obj.children.get(0);
    }

    /*public int sumOfParent(Node obj) {
        if (obj.parentNode != null) {
            sum += obj.parentNode.time;
            parentsToString(obj.parentNode);
        }
        this.count = 0;
        return this.sum;
    }*/

    public void cleanResultSum() {
        this.sum = 0;
    }

    public void itter(Node obj) {//печать на экран
        int temp = 0;
        for (Node i : obj.children) {
            if (!i.children.isEmpty()) {
                itter(i);
            } else {
                System.out.println(" ");
                System.out.print(parentsToString(i));
                temp += parentsSum(i) + i.time;
                System.out.print("(n:" + i.name + " t:" + i.time + "); " + "sum of time: " + temp);
                temp = 0;
                cleanResultString();
                cleanResultSum();
            }
        }
    }
}

