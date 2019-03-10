public class Main {
    public static void main(String[] args) {
        ColorModel x = new RGB24bit(23, 55, 100);
        ColorModel x1 = new HSV(360, 30, 7);
        ColorModel x2 = new HSV(6, 23, 100);
        ColorModel x3 = new RGB24bit(67, 56, 27);
        ColorModel x4 = new RGB24bit(7, 46, 94);
        ColorModel x5 = new Grayscale8bit(255);
        ColorModel x6 = new RGB24bit(7, 156, 6);
        ColorModel x7 = new Grayscale8bit(65);

        ColorPalette palette = new ColorPalette(100);
        palette.add(x);
        palette.add(x1);
        palette.add(x2);
        palette.add(x3);
        palette.add(x4);
        palette.add(x5);
        palette.add(x6);
        palette.add(x7);

        ColorModel kk = new HSV(1, 1, 1);

        System.out.println(palette.findClosestColor(kk).toString());
    }
}
