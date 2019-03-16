import java.util.List;

public class Chessboard {
    private int x;
    private int y;
    private static final int ASCII_CONST_LETTERS = 98;
    private static final int ASCII_CONST_NUMBERS = 49;

    public Chessboard(int x, int y) {
        check(x, y);
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        check(x, y);
        this.x = x;
        this.y = y;
    }

    public int[] getXY() {
        return new int[]{this.x, this.y};
    }

    private void check(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("invalid arguments");
        }
    }

    public int[] convertToInt(String array) {
        if (array.length() != 2) {
            throw new IllegalArgumentException("lenght != 2");
        }
        int oX = array.charAt(0) - ASCII_CONST_LETTERS;
        int oY = array.charAt(1) - ASCII_CONST_NUMBERS;
        check(oX, oY);
        return new int[] {oX + 1, oY + 1};
    }

    public String convertToStr(int x, int y) {
        return (char)(x + ASCII_CONST_LETTERS) + "" + (char)(y + ASCII_CONST_NUMBERS );
    }

    public void print(String[] str) {
        for (String i: str) {
            System.out.println(i);
        }
    }

    public void getCheckArray(String[] array) {
        print(array);

        int startX = convertToInt(array[0])[0];
        int startY =convertToInt(array[0])[1];


        for (int i = 1; i < array.length; i++ ) {
            //System.out.println(Math.abs(convert(array[i])[0] - startX) + " " + Math.abs(convert(array[i])[1] - startY));

            if((Math.abs(convertToInt(array[i])[0] - startX) == 1) && (Math.abs(convertToInt(array[i])[1] - startY) == 2 )) {
                System.out.print(startX + "" + startY + "->" + convertToInt(array[i])[0] + "" + convertToInt(array[i])[1] + " ");
                //System.out.print(convertToStr(startX, startX) + "->" + convertToStr(convertToInt(array[i])[0],convertToInt(array[i])[1]) + " ");
                startX = convertToInt(array[i])[0];
                startY = convertToInt(array[i])[1];
            }

            if((Math.abs(convertToInt(array[i])[0] - startX) == 2) && (Math.abs(convertToInt(array[i])[1]  - startY) == 1)) {
                System.out.print(startX + "" + startY + "->" + convertToInt(array[i])[0] + "" + convertToInt(array[i])[1] + " ");
                //System.out.print(convertToStr(startX, startX) + "->" + convertToStr(convertToInt(array[i])[0],convertToInt(array[i])[1]) + " ");
                startX = convertToInt(array[i])[0];
                startY = convertToInt(array[i])[1];
            }
        }
    }

    @Override
    public String toString() {
        return Character.toString((char) (ASCII_CONST_LETTERS + this.x)) + (this.y + 1);
    }




    class MyExceptionClass extends Throwable {
        private String result;

        public MyExceptionClass(String message, String result) {
            super(message);
            this.result = result;
        }
    }
}
