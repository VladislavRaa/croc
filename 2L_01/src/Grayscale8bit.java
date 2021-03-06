final class Grayscale8bit implements ColorModel {

    private int grayScale;

    Grayscale8bit(int grayScale) {
        check(grayScale);
        this.grayScale = grayScale;
    }

    public void set(int grayScale) {
        check(grayScale);
        this.grayScale = grayScale;
    }

    public int get() {
        return this.grayScale;
    }

    private void check(int grayScale) {
        if (grayScale < 0 || grayScale > 255) {
            throw new IllegalArgumentException("0 .. 255");
        }
    }

    public final int[] toRGB() {
        return new int[]{this.grayScale, this.grayScale, this.grayScale};
    }

    @Override
    public String toString() {
        return "grayScale: " + this.grayScale;

    }
}