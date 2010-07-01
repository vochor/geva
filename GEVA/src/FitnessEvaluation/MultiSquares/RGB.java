package FitnessEvaluation.MultiSquares;

/**
 * A static-style class, useful for transferring data between JScheme and Java
 * in the BooleanGrammarSwingPainter class.
 *
 * @author jmmcd
 */

public class RGB {
    static int [] data;
    public static int width;
    public static int height;
    public static void init(int _width, int _height) {
	width = _width;
	height = _height;
	data = new int[width * height];
    }
    public RGB(int _width, int _height) {
	width = _width;
	height = _height;
	data = new int[width * height];
    }
    public static void set(int x, int y, int val) {
	data[x + width * y] = val;
    }
    public static int[] getAllData() {
	return data;
    }
}

