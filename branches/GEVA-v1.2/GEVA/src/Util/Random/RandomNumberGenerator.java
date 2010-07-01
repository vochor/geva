package Util.Random;

/**
 * Interface for number generators
 */
public interface RandomNumberGenerator {

    /**
     * Get the next int, where n is max
     * @param n max in value
     * @return next random int
     */
    public int nextInt(int n);

    /**
     * Get the next int
     * @return next int
     */
    public int nextInt();

    /**
     * Get the next double 0<=x<1
     * @return next double
     */
    public double nextDouble();

    /**
     * Get the next short
     * @return next short
     */
    public short nextShort();

    /**
     * Get the next char
     * @return next char
     */
    public char nextChar();

    /**
     * Get the next boolean
     * @return next boolean
     */
    public boolean nextBoolean();

    /**
     * Get the next boolean with probability of true defined by d
     * 0<=d<=1
     * @param d probablity of true
     * @return next boolean
     */
    public boolean nextBoolean(double d);

    /**
     * Set the seed
     * @param l seed
     */
    public void setSeed(long l);

    /**
     * Set the seed
     * @param aI seed
     */
    public void setSeed(int[] aI);
}