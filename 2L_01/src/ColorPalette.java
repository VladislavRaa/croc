class ColorPalette {
    //набора цветов фиксированного размера
    private ColorModel[] palette;
    private int count = 0;
    private int sizeOfPallete;

    ColorPalette(int sizeOfPallete) {
        this.sizeOfPallete = sizeOfPallete;
        palette = new ColorModel[sizeOfPallete];
    }

    void add(ColorModel obj) {
        check(count);
        palette[count] = obj;
        count++;
    }

    public ColorModel get(int count) {
        check(count);
        return palette[count];
    }

    double proximityMetric(ColorModel obj1, ColorModel obj2) {

        int drObj1 = obj1.toRGB()[0];
        int dgObj1 = obj1.toRGB()[1];
        int dbObj1 = obj1.toRGB()[2];

        int drObj2 = obj2.toRGB()[0];
        int dgObj2 = obj2.toRGB()[1];
        int dbObj2 = obj2.toRGB()[2];

        return Math.sqrt(Math.pow((drObj1 - drObj2), 2) + Math.pow((dgObj1 - dgObj2), 2) + Math.pow((dbObj1 - dbObj2), 2));
    }

    ColorModel findClosestColor(ColorModel obj) {
        ColorModel closestColor;
        double deltaColor = proximityMetric(obj, palette[0]);
        int result = 0;
        for (int i = 0; i < count; i++) {
            System.out.println(proximityMetric(obj, palette[i]));
            double temp = proximityMetric(obj, palette[i]);
            if (temp < deltaColor) {
                deltaColor = temp;
                result = i;
            }
        }
        return palette[result];
    }

    private void check(int count) {
        if (count >= this.sizeOfPallete) {
            throw new IndexOutOfBoundsException("Maximum size of a palette: " + this.sizeOfPallete);
        }
    }
}
