interface ColorModel {
    int[] toRGB();
    StringBuilder print();
}

class ColorPalette {

    private static ColorModel[] palette = new ColorModel[8];
    private int count = 0;

    void add(ColorModel obj) {
        palette[count] = obj;
        count++;
    }
    public ColorModel get(int count) {
        return palette[count];
    }

    static double proximityMetric(ColorModel obj1, ColorModel obj2) {

        int drObj1 = obj1.toRGB()[0];
        int dgObj1 = obj1.toRGB()[1];
        int dbObj1 = obj1.toRGB()[2];

        int drObj2 = obj2.toRGB()[0];
        int dgObj2 = obj2.toRGB()[1];
        int dbObj2 = obj2.toRGB()[2];

        return Math.sqrt(Math.pow((drObj1 - drObj2), 2) + Math.pow((dgObj1 - dgObj2), 2) + Math.pow((dbObj1 - dbObj2), 2));
    }

    static ColorModel d_rgb(ColorModel obj) {
        ColorModel closestColor;
        double deltaColor = proximityMetric(obj, palette[0]);
        int result = 0;
        for (int i = 0; i < palette.length; i++) {
                System.out.println(proximityMetric(obj, palette[i]));
                if(proximityMetric(obj, palette[i] ) < deltaColor){
                    deltaColor = proximityMetric(obj, palette[i] );
                    result = i;
                }
            }
        System.out.println("result: " + deltaColor);
        return palette[result];
    }
}
