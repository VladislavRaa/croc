final class HSV implements ColorModel {
    private int hue; //0 .. 360
    private int saturation; // 0 .. 100
    private int value; // 0 .. 100

    HSV(int hue, int saturation, int value) {
        check(hue, saturation, value);
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    private void check(int hue, int saturation, int value) {
        if (hue < 0 || hue > 360 || saturation < 0 || saturation > 100 || value < 0 || value > 100) {
            throw new IllegalArgumentException("(0 .. 255, 0 .. 255, 0 .. 255)");
        }
    }

    public void set(int hue, int saturation, int value) {
        check(hue, saturation, value);
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public final int[] get() {
        return new int[]{this.hue, this.saturation, this.value};
    }

    private int[] toColoref(int x, int y, int z) {
        return new int[]{x * 255 / 100, y * 255 / 100, z * 255 / 100};
    }

    public final int[] toRGB() {
        int hI = (this.hue / 60) % 6;
        int vMin = ((100 * this.saturation) * this.value) / 100;
        int koefA = (this.value - vMin) * (hue % 60) / 60;
        int vInc = vMin + koefA;
        int vDec = this.saturation - koefA;

        switch (hI) {
            case 0:
                return toColoref(this.value, vInc, vMin);
            case 1:
                return toColoref(vDec, this.value, vMin);
            case 2:
                return toColoref(vMin, this.value, vInc);
            case 3:
                return toColoref(vMin, vDec, this.value);
            case 4:
                return toColoref(vInc, vMin, this.value);
            case 5:
                return toColoref(this.value, vMin, vDec);
            default:
                throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        }
    }

    @Override
    public String toString() {
        return "HSV(hue: " + this.hue + ", saturation: " + this.saturation + ", value: " + this.value + ")";
    }
}
