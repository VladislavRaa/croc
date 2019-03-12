final class RGB24bit implements ColorModel {
    private int red;
    private int green;
    private int blue;

    RGB24bit(int red, int green, int blue) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void set(int red, int green, int blue) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public final int[] get() {
        return new int[]{this.red, this.green, this.blue};
    }

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("(0 .. 255, 0 .. 255, 0 .. 255)");
        }
    }

    public final int[] toRGB() {
        return new int[]{this.red, this.green, this.blue};
    }

    @Override
    public String toString() {
        return "RGB: (" + this.red + ", " + this.green + ", " + this.blue + ")";
    }
}