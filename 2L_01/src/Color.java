import java.util.ArrayList;

abstract class Color{

}

class Grayscale8bit extends Color implements ColorModel {

    private int grayScale;

    Grayscale8bit(int grayScale) {
        //от 0 до 255.
        this.grayScale = grayScale;
    }

    public void setGrayScale(int grayScale) {
        this.grayScale = grayScale;
    }

    public int getGrayScale() {
        return this.grayScale;
    }

    public int toRGB() {
        return this.grayScale * 100000 + this.grayScale * 100 + this.grayScale;
    }
}

class RGB24bit extends Color implements ColorModel {
    private int red;
    private int green;
    private int blue;

    RGB24bit(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int toRGB() {
        return this.red * 100000 + this.green * 100 + this.blue;
    }
}

class HSV extends Color implements ColorModel {
    private int hue; //0 .. 360
    private int saturation; // 0 .. 100
    private int value; // 0 .. 100

    HSV(int hue, int saturation, int value){
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public int toRGB() {
        int hI = (this.hue / 60) % 6;
        int vMin = ((100 * this.saturation) * this.value) / 100;
        int a = (this.saturation - vMin) * (hI % 6) / 60;
        int vInc = vMin + a;
        int vDec = this.saturation - a;

        switch (hI) {
            case 0: return 0;
            case 1: return 0;
            case 2: return 0;
            case 3: return 0;
            case 4: return 0;
            case 5: return 0;
        }
        return 0;
    }
}

interface ColorModel {
    int toRGB();
}

class ColorPalette {
    ArrayList mas = new ArrayList(10);
    void add(Color obj) {
        mas.add(obj);
    }
    static int proximityMetric(RGB24bit obj1, RGB24bit obj2) {return -1;}
    static Color d_rgb(Color obj1, Color obj2) {return obj1;}
}
