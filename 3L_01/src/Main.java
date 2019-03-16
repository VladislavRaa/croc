public class Main {

    public static void main(String[] args) {
        Chessboard test = new Chessboard(4,2);
        String[] mas = {"g8", "e7", "c8"};
        test.getCheckArray(mas);
        //System.out.println(test.toString());
    }
}
