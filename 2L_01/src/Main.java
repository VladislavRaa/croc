public class Main {
    public static void main(String[] args) {
        /*Grayscale8bit a = new Grayscale8bit(100);
        Grayscale8bit b = new Grayscale8bit(100);
        a.toRGB();
        ColorPalette zzz = new ColorPalette();
        ColorModel t = new Grayscale8bit(100);
        ColorModel x = new RGB24bit(1, 1, 100);
        ColorModel d = new HSV(4,3,4);*/
        //System.out.println(a.toRGB());

        ColorModel x = new RGB24bit(1, 1, 100);
        ColorModel x1 = new HSV(46, 1, 100);
        ColorModel x2 = new HSV(6, 23, 100);
        ColorModel x3 = new RGB24bit(67, 4, 6);
        ColorModel x4 = new RGB24bit(7, 46, 6);
        ColorModel x5 = new Grayscale8bit(6);
        ColorModel x6 = new RGB24bit(7, 0, 6);
        ColorModel x7 = new Grayscale8bit(65);

        ColorPalette palette = new ColorPalette();
        palette.add(x);
        palette.add(x1);
        palette.add(x2);
        palette.add(x3);
        palette.add(x4);
        palette.add(x5);
        palette.add(x6);
        palette.add(x7);
        ColorModel zzzz = new RGB24bit(1, 4, 5);
        System.out.println(ColorPalette.d_rgb(zzzz).print());

    }
}
