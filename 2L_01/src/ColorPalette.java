interface ColorModel {
    int[] toRGB();

    StringBuilder print();
}

class ColorPalette {
    //набора цветов фиксированного размера
    private static ColorModel[] palette = new ColorModel[100];
    private static int count = 0;

    void add(ColorModel obj) {
        check(count);
        palette[count] = obj;
        count++;
    }

    public ColorModel get(int count) {
        check(count);
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

    static ColorModel findClosestColor(ColorModel obj) {
        ColorModel closestColor;
        double deltaColor = proximityMetric(obj, palette[0]);
        int result = 0;
        for (int i = 0; i < count; i++) {
            System.out.println(proximityMetric(obj, palette[i]));
            if (proximityMetric(obj, palette[i]) < deltaColor) {
                deltaColor = proximityMetric(obj, palette[i]);
                result = i;
            }
        }
        //System.out.println("result: " + deltaColor);
        return palette[result];
    }

    private void check(int count) {
        if (count > 100) {
            throw new IndexOutOfBoundsException("Maximum size of a palette of 100 elements");
        }
    }

    //private static ArrayList<ColorModel> p = new ArrayList<ColorModel>();
    /*void add(ColorModel obj) {
            p.add(obj);
        }*/

    /*static ColorModel d_rgb__(ColorModel obj) {
        ColorModel closestColor;
        double deltaColor = proximityMetric(obj, p.get(0));
        int result = 0;
        for (int i = 0; i < p.size(); i++) {
            System.out.println(proximityMetric(obj, p.get(i)));
            if (proximityMetric(obj, p.get(i)) < deltaColor) {
                deltaColor = proximityMetric(obj, p.get(i));
                result = i;
            }
        }
        System.out.println("result: " + deltaColor);
        return p.get(result);
    }*/
}
