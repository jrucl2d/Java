package utils;

public class Shape {
    private static final String HEXAGON = "HEXAGON";
    private static final String OCTAGON = "OCTAGON";
    private static final String RECTANGLE = "RECTANGLE";
    private static final String TRIANGLE = "TRIANGLE";
    private static final String DIAMOND = "DIAMOND";
    private static final String PENTAGON = "PENTAGON";
    private static final String BALL = "BALL";
    private static final String STAR = "STAR";

    public static String getColor(String shape) {
        if (shape.endsWith("<>")) {
            return shape.replace("<>", "").trim();
        }
        int hyphen = shape.indexOf("-");
        if (hyphen > 0) {
            return shape.substring(0, hyphen);
        }
        return shape;
    }

    public static String getSuffix(String shape) {
        if (HEXAGON.equals(shape)) return "-H";
        if (OCTAGON.equals(shape)) return "-O";
        if (RECTANGLE.equals(shape)) return "-R";
        if (TRIANGLE.equals(shape)) return "-T";
        if (DIAMOND.equals(shape)) return "<>";
        if (PENTAGON.equals(shape)) return "-P";
        if (STAR.equals(shape)) return "-S";
        return ""; // 원
    }
}
