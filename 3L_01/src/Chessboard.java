public class Chessboard {
    private int x;
    private int y;
    private static final int ASCII_CONST_LETTERS = 97;
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

    private int[] convertToInt(String array) throws MyExceptionClass {
        if (array.length() != 2) {
            throw new MyExceptionClass("lenght != 2");
        }
        int oX = array.charAt(0) - ASCII_CONST_LETTERS;
        int oY = array.charAt(1) - ASCII_CONST_NUMBERS;

        check(oX, oY);
        return new int[]{oX, oY};
    }

    public String convertToStr(int x, int y) {
        return (char) (x + ASCII_CONST_LETTERS) + "" + (char) (y + ASCII_CONST_NUMBERS);
    }

    public String convertToStr() {
        return (char) (x + ASCII_CONST_LETTERS) + "" + (char) (y + ASCII_CONST_NUMBERS);
    }

    private void print(String[] str) {
        for (String i : str) {
            System.out.println(i);
        }
    }

    private boolean checkFromTo(String from, String to) throws MyExceptionClass {
        if ((Math.abs(convertToInt(to)[0] - convertToInt(from)[0]) == 1) && (Math.abs(convertToInt(to)[1] - convertToInt(from)[1]) == 2)) {
            return true;
        }
        if ((Math.abs(convertToInt(to)[0] - convertToInt(from)[0]) == 2) && (Math.abs(convertToInt(to)[1] - convertToInt(from)[1]) == 1)) {
            return true;
        }
        throw new MyExceptionClass("конь так не ходит: " + from + " -> " + to);
    }

    public void checkArray(String[] array) {
        try {
            for (int i = 0; i < array.length - 1; i++) {
                checkFromTo(array[i], array[i + 1]);
            }
            System.out.println("OK");
        } catch (MyExceptionClass e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return Character.toString((char) (ASCII_CONST_LETTERS + this.x)) + (this.y + 1);
    }

    class MyExceptionClass extends Exception {
        public MyExceptionClass(String message) {
            super(message);
        }
    }
}
