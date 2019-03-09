public class Main {
    public static void main(String[] args) {
        Grayscale8bit a = new Grayscale8bit(100);
        Grayscale8bit b = new Grayscale8bit(100);
        a.toRGB();
        ColorPalette zzz = new ColorPalette();
        zzz.add(a);
        zzz.d_rgb(a,b);
        System.out.println("Hello World!");
    }
}
