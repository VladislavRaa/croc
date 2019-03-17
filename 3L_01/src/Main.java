public class Main {

    public static void main(String[] args) throws Chessboard.MyExceptionClass {
        Chessboard test = new Chessboard(1,1);
        //System.out.println(test.toString());
        String[] mas = {"g3", "a3", "c4", "a1"};
        String[] mas1 = {"g8", "e7", "e6"};
        String[] mas2 = {"g8", "e7", "c8"};
        //test.checkArray(mas);
        //test.checkArray(mas1);
        test.checkArray(mas2);
        //System.out.println(test.toString());
    }
}
